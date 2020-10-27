/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.manager;

import entity.Person;
import entity.Journal;
import entity.Subject;
import java.util.List;
import java.util.Scanner;
import tools.savers.SaveToFile;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author user
 */
public class JournalManager implements Serializable {
    private Scanner scanner = new Scanner(System.in);
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.YYYY");
    public void setMarkToUser(List<Subject> listSubjects, List<Person> listPersons, List<Journal> listJournals) {
        System.out.println("--- Выставить оценку ---");
        System.out.println("Список предметов: ");
        for (int i = 0; i < listSubjects.size(); i++) {
            Subject subject = listSubjects.get(i);
            System.out.printf("%d. %s. Часов: %d  Преподаватель: %s"
                    ,i+1
                    ,subject.getName()
                    ,subject.getDuration()
                    ,subject.getTeacher()
            );
        }
        System.out.println("Выбрать предмет: ");
        int numSubject;
        do{
            String numSubjectStr = scanner.nextLine();
            try {
                numSubject = Integer.parseInt(numSubjectStr);
                if(numSubject > 0 && numSubject <= listSubjects.size()) {
                   break; 
                }else{
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Выберите номер предмета: ");
            }
        }while(true);
        Subject subject = listSubjects.get(numSubject-1);
        System.out.println("Список учеников: ");
        for (int i = 0; i < listPersons.size(); i++) {
            Person person = listPersons.get(i);
            Journal journal = getOneJournal(person, subject, listJournals);
            if ("STUDENT".equals(person.getRole())) {
                System.out.printf("%d. %s %s, оценка по предмету: %s - %d, поставлена: %s"
                        ,i+1
                        ,person.getFirstname()
                        ,person.getLastname()
                        ,subject.getName()
                        ,journal.getMark()
                        ,sdf.format(journal.getDate())
                        
                );
            }
        }
        System.out.println("Выбрать ученика: ");
        int numStudent;
        do {
            String numStudentStr = scanner.nextLine();
            try{
                numSubject = Integer.parseInt(numStudentStr);
                if (numSubject > 0 && numSubject <= listSubjects.size()) {
                    break;
                }else{
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Введите номер студента: ");
            }
        } while (true);
        Calendar c = new GregorianCalendar();
        int mark = 0;
        Person Student = listPersons.get(numSubject-1);
        
    }

    private Journal getOneJournal(Person person, Subject subject, List<Journal> listJournals) {
        for (int i = 0; i < listJournals.size(); i++) {
            Journal journal = listJournals.get(i);
            if (person.equals(journal.getStudent()) && subject.equals(journal.getSubject())) {
                return journal;
            }
        }
        return new Journal();
    }
}
