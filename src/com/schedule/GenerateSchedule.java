package com.schedule;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import com.courses.CourseEvent;
import com.courses.Courses;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import com.faculty.Faculty;
import com.faculty.FacultyEvent;
import com.importfile.Student;
import com.importfile.StudentCourse;
import com.report.StudentReport;
import com.report.TeacherReport;

public class GenerateSchedule {
	
	private static final String FILE_PATH_STUDENT = "C:/Input/StudentDetails.csv";
	private static final String FILE_PATH_STUDENT_COURSE = "C:/Input/StudentCourseDetails.csv";
	private static final String FILE_PATH = "C:/Input/Schedule.csv";
	private static final String FILE_PATH_TEACHER_REPORT = "C:/Input/TeacherReport.csv";
	
	public ArrayList<Courses> courseList;
	public ArrayList<Faculty> teacherList;
	public ArrayList<Student> studentList;
	public ArrayList<StudentCourse> studentCourseList;
	public ArrayList<SchedulePojo> studentCourseMap;
	public ArrayList<ScheduleSection> sectionList;
	public ArrayList<Teacher> teacherCourseMap;
	public ArrayList<String> yearSem=new ArrayList<String>();
	private  String semester;
	private int maxLoad;
	
	public GenerateSchedule() throws Exception{
		}
	
	public void readData() throws Exception{
		CourseEvent ce=new CourseEvent();
		courseList=ce.getAllCourse();
		
		FacultyEvent fe=new FacultyEvent();
		teacherList=fe.getAllFaculty();
		
		
		CsvReader student = new CsvReader(FILE_PATH_STUDENT);
		studentList=new ArrayList<Student>();
		int c1=0;
		while(student.readRecord()){
			if(c1>0){
				Student r=new Student();
				 r.setStudentId(student.get(0));
				 r.setDegreeID(student.get(1));
				 r.setYearSem(student.get(2));
				 studentList.add(r);
				
			}
			c1++;
		}
		student.close();
		
		int semCount=0;
		for(Student s:studentList){
			if(semCount>0&&!yearSem.contains(s.getYearSem()))
				yearSem.add(s.getYearSem());
			else if(semCount==0)
				yearSem.add(s.getYearSem());
			semCount++;
		}

		
		
		CsvReader studentCourse = new CsvReader(FILE_PATH_STUDENT_COURSE);
		studentCourseList=new ArrayList<StudentCourse>();
		while(studentCourse.readRecord()){
			StudentCourse r=new StudentCourse();
			 r.setStudentId(studentCourse.get(0));
			 r.setCourseId(studentCourse.get(1));
			 r.setCourseName(studentCourse.get(2));
			 r.setYearSem(studentCourse.get(3));
			 r.setGrade(studentCourse.get(4));
			 studentCourseList.add(r);
		}
		studentCourse.close();	
		
		
		for(StudentCourse sc:studentCourseList){
			for(Student s:studentList){
				if(sc.getStudentId().equals(s.getStudentId()))
					sc.setYearSem(s.getYearSem());
			}
		}
		
		
		studentCourseMap= new ArrayList<SchedulePojo>();
		for(Courses c:courseList){
			String courseId="";
			int studentCount=0;
			SchedulePojo sp=new SchedulePojo();
			ArrayList<String> allStudents=new ArrayList<String>();
			for(StudentCourse sc:studentCourseList){
				courseId=c.getNumber();
				if(c.getNumber().equals(sc.getCourseId())){
					allStudents.add(sc.getStudentId()+","+sc.getYearSem());
					studentCount++;
				}				
			}
			sp.setAllStudents(allStudents);
			sp.setFallS(c.getFallS());
			sp.setSpringS(c.getSpringS());
			sp.setSummerS(c.getSummerS());
			sp.setHours(c.getHours());
			sp.setCount(studentCount);
			sp.setCourse(courseId);
			sp.setCourseName(c.getName());
			sp.setTeacher(c.getTeacher());
			studentCourseMap.add(sp);
		}
		
		for(SchedulePojo sp: studentCourseMap){
			if(sp.getCount()!=0){
				CourseEvent cev=new CourseEvent();
				Courses c=cev.getCourse(sp.getCourse());
				sp.setTeacher(c.getTeacher());
			}
		}
		sectionList= new ArrayList<ScheduleSection>();
		
		/*teacherCourseMap=new ArrayList<Teacher>();
		for(Faculty f:teacherList){
			Teacher t=new Teacher();
			t.setTeacherName(f.getLastName());
			t.setTeacherAvlDays(f.getAvlDays());
			t.setTeacherAvlDaysCount(f.getAvlDays().length());
			t.settAllocated(0);
			t.settAvailable(f.getAvlDays().length()*25);
			t.settAllCourses(f.getCourses());
			t.setFallLoad(f.getFallLoad());
			t.setSummerLoad(f.getSummerLoad());
			t.setSpringLoad(f.getSpringLoad());
			teacherCourseMap.add(t);
		}*/
		
		
		for(String sem:yearSem){
			String year=sem.substring(0, 4);
			semester=sem.substring(4, 6);
			boolean offered=false;
			resetTeacher(semester);
			for (SchedulePojo sp:studentCourseMap){
				//System.out.println(sp.getCourse());
				if(semester.equals("FA"))
					if(sp.getFallS().equals("Y"))
						offered=true;
				if(semester.equals("SU"))
					if(sp.getSummerS().equals("Y"))
						offered=true;
				if(semester.equals("SP"))
					if(sp.getSpringS().equals("Y"))
						offered=true;
				
				int scount=0;
				
				for(String s:sp.getAllStudents()){
					if(s.split(",")[1].equals(sem)){
						if(null==sp.getStudents())
							sp.setStudents(s.split(",")[0]);
						else
							sp.setStudents(sp.getStudents()+","+s.split(",")[0]);
						
						scount++;
					}
				}
				
				
				if(offered){
					if(scount<=25&&scount!=0){
						ScheduleSection ss=new ScheduleSection();
						ss.setSectionId(sp.getCourse()+"-"+sem+"-001");
						ss.setCourseId(sp.getCourse());			
						ss.setsCount(scount);
						ss.setCourseName(sp.getCourseName());
						ss.setStudents(sp.getStudents());
						int studentCount=sp.getCount();
						String[] teacher=sp.getTeacher().split(",");
						String[] teacherDetails=getTeacher(teacher,studentCount,sp.getCourse()+"-001");
						ss.setTeacherName(teacherDetails[0]);
						ss.settDay(teacherDetails[1]);	
						ss.setYearSem(sem);
						sectionList.add(ss);
					}else if(scount!=0){
						int total=scount;
						int sectionCount=scount/25;
						String secStudents[]=sp.getStudents().split(",");
						if(scount%25>0)
							sectionCount=sectionCount+1;
						int secId=1;
						int k=0;
						while(sectionCount!=0){					
							ScheduleSection ss=new ScheduleSection();
							ss.setSectionId(sp.getCourse()+"-"+sem+"-00"+secId);
							ss.setCourseId(sp.getCourse());
							ss.setCourseName(sp.getCourseName());
							int studentCount;
							sectionCount--;
							if(total>25){
								for(;k<25*secId;k++){
									if(null==ss.getStudents())
										ss.setStudents(secStudents[k]);
									else
										ss.setStudents(ss.getStudents()+","+secStudents[k]);
									
								}
								ss.setsCount(25);
								studentCount=25;
								total=total-25;
							}
							else{
								for(;k<secStudents.length;k++){
									if(null==ss.getStudents())
										ss.setStudents(secStudents[k]);
									else
										ss.setStudents(ss.getStudents()+","+secStudents[k]);
									
								}
								ss.setsCount(total%25);
								studentCount=total%25;
							}
							String[] teacher=sp.getTeacher().split(",");
							String[] teacherDetails=getTeacher(teacher,studentCount,sp.getCourse()+"-00"+secId);
							ss.setTeacherName(teacherDetails[0]);
							ss.settDay(teacherDetails[1]);
							ss.setYearSem(sem);
							sectionList.add(ss);
							secId++;
						}
					}
					
				}
					
			}
		}
		
		File file = new File(FILE_PATH);
		file.delete();
        CsvWriter csvOutput = new CsvWriter(new FileWriter(FILE_PATH, true), ',');  
        csvOutput.write("CourseCode");
        csvOutput.write("CourseName");
        csvOutput.write("SectionId");
        csvOutput.write("StudentCount");
        csvOutput.write("TeacherName");
        csvOutput.write("Students");
        csvOutput.write("TeacherAvailableDay");
        csvOutput.write("YearSem");
        csvOutput.endRecord();
        for(ScheduleSection sp:sectionList){
        	csvOutput.write(sp.getCourseId());
            csvOutput.write(sp.getCourseName());
            csvOutput.write(sp.getSectionId());
            csvOutput.write(Integer.toString(sp.getsCount()));
            csvOutput.write(sp.getTeacherName());
            csvOutput.write(sp.getStudents());
            csvOutput.write(sp.gettDay());
            csvOutput.write(sp.getYearSem());

            csvOutput.endRecord();
		}
        csvOutput.close();
        
        
       /* File fileT = new File(FILE_PATH_TEACHER_REPORT);
		fileT.delete();
        CsvWriter csvOutputT = new CsvWriter(new FileWriter(FILE_PATH_TEACHER_REPORT, true), ',');  
        csvOutputT.write("TeacherName");
        csvOutputT.write("Allocated Courses");
        csvOutputT.write("Sections Assigned");
        csvOutputT.write("Allocated Sections");
        csvOutputT.write("Total Availabe Days");
        csvOutputT.write("Unasigned Days");
        csvOutputT.endRecord();
        for(Teacher t:teacherCourseMap){
        	csvOutputT.write(t.getTeacherName());
        	csvOutputT.write(t.gettAllCourses());
        	csvOutputT.write(t.gettCourses());
        	csvOutputT.write(t.gettSections());
        	csvOutputT.write(t.getTeacherAvlDaysStatic());
        	csvOutputT.write(t.getTeacherAvlDays());
        	csvOutputT.endRecord();
		}
        csvOutputT.close();*/
        
        StudentReport sr=new StudentReport();
        sr.generate();
        
        TeacherReport tr=new TeacherReport();
        tr.generate();
		/*for(ScheduleSection sp:sectionList){
			System.out.println(sp.getSectionId()+" "+ sp.getCourseId()+" "+sp.getsCount()+" "+sp.getTeacherName()+"  "+sp.gettDay());
		}*/
		
	}
	
	private void resetTeacher(String semester2) {
		teacherCourseMap=new ArrayList<Teacher>();		
		for(Faculty f:teacherList){
			Teacher t=new Teacher();
			t.setTeacherName(f.getLastName());
			t.setTeacherAvlDays(f.getAvlDays());
			t.setTeacherAvlDaysCount(f.getAvlDays().length());
			t.settAllocated(0);
			t.settAvailable(f.getAvlDays().length()*25);
			t.settAllCourses(f.getCourses());
			t.setFallLoad(f.getFallLoad());
			t.setSummerLoad(f.getSummerLoad());
			t.setSpringLoad(f.getSpringLoad());
			if(semester.equals("FA"))
				t.setMaxLoad(Integer.parseInt(f.getFallLoad()));
			if(semester.equals("SU"))
				t.setMaxLoad(Integer.parseInt(f.getSummerLoad()));
			if(semester.equals("SP"))
				t.setMaxLoad(Integer.parseInt(f.getSpringLoad()));			
			teacherCourseMap.add(t);
		}

		
	}

	public String[] getTeacher(String[]teacher,int studentCount, String SectionId){		
		
		String teacherDetail[] = new String[2];
		if(teacher.length!=0){
			for(Teacher t:teacherCourseMap){
				for(int i=0;i<teacher.length;i++){
					if(t.getTeacherName().equals(teacher[i])){
						if(t.gettAvailable()>=studentCount&&t.getMaxLoad()>0){
							//teacherName=t.getTeacherName();
							//avlDay=t.getTeacherAvlDays().charAt(0);
							teacherDetail[0]=t.getTeacherName();
							if(t.getTeacherAvlDays().charAt(0)=='M')
								teacherDetail[1]="Monday";
							if(t.getTeacherAvlDays().charAt(0)=='T')
								teacherDetail[1]="Tuesday";
							if(t.getTeacherAvlDays().charAt(0)=='W')
								teacherDetail[1]="Wednesday";
							if(t.getTeacherAvlDays().charAt(0)=='R')
								teacherDetail[1]="Thursday";
							if(t.getTeacherAvlDays().charAt(0)=='F')
								teacherDetail[1]="Friday";
							//teacherDetail[1]=Character.toString(t.getTeacherAvlDays().charAt(0));
							
							if(null==t.getTeacherAvlDaysStatic())
								t.setTeacherAvlDaysStatic(t.getTeacherAvlDays());
							if(null==t.gettCourses())
								t.settCourses(SectionId.split("-")[0]);
							else
								t.settCourses(t.gettCourses()+","+SectionId.split("-")[0]);
							
							if(null==t.gettSections())
								t.settSections(SectionId);
							else
								t.settSections(t.gettSections()+","+SectionId);
							
							t.settAllocated(t.gettAllocated()+25);
							t.settAvailable(t.gettAvailable()-25);
							t.setTeacherAvlDays(t.getTeacherAvlDays().substring(1, t.getTeacherAvlDays().length()));
							t.setTeacherAvlDaysCount(t.getTeacherAvlDaysCount()-1);
							t.setMaxLoad(t.getMaxLoad()-1);
							return teacherDetail;
						}
					}else{
						teacherDetail[0]="Not available";
						teacherDetail[1]="Not available";
					}					
				}
			}					
		}
		return teacherDetail;

		
	}
}
