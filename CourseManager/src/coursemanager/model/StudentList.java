/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package coursemanager.model;

import java.util.InputMismatchException;
import java.util.Scanner;
import coursemanager.util.Formatter;


/**
 *
 * @author NgocHien-PC
 */
public class StudentList extends CommonList<Student> {   
    Scanner sc = new Scanner(System.in);    
    
    private String inputScode(){                
        String scode = sc.nextLine();          
        return Formatter.normalizeId(scode);                                       
    }
    
    private String inputName(){        
        String name = sc.nextLine();
        return Formatter.normalizeName(name);
    }
    
    private int inputByear(){
       while(true){
           try{
            int byear = sc.nextInt();
            if(byear <= 0){
                throw new InputMismatchException();
            }
           }catch(InputMismatchException e){
               System.out.println("Input a positive integer");
           }
       }
    }
    
    public void addStudent(){
        String scode = inputScode();
        String name = inputName();
        int byear = inputByear();
        if(searchByScode(scode) == null){
            Student newStudent = new Student(scode, name, byear);
            super.addLast(newStudent);
        }else{
            System.out.println("Duplicated Student Code");
        }
    }
    
    public void addStudent(Student newStudent){
        if(searchByScode(newStudent.getScode()) == null){            
            super.addLast(newStudent);
        }else{
            System.out.println("Duplicated Student Code");
        }
    }
    
    @Override
    public CommonList<Student> sort() {       
        for( Node<Student> p = head; p != null; p = p.next){
            for( Node<Student> q = p.next; q != null; q = q.next){
                if(p.data.getScode().compareTo(q.data.getScode()) < 0){
                    Student t = p.data;
                    p.data = q.data;
                    q.data = t;
                }
            }
        }return this;
    }

    @Override
    public void display() {
        if(!this.isEmpty()){
            Node<Student> p = head;
            
            while(p != null){
                System.out.printf("%-5s%-15s%-3d\n", p.data.getScode(), p.data.getName(), p.data.getByear());                
                p = p.next;
            }
        }
    }
    
    public Node<Student> searchByScode(){
        String scode = inputScode();
        Node<Student> result = null;
        if(!this.isEmpty()){
            Node<Student> p = head;
            while( p != null ){
                if( p.data.getScode().equals(scode)){
                    result = p;
                    break;
                }
            }
        }
        return result;
    }
    
    public Node<Student> searchByScode(String scode){
        Node<Student> result = null;
        if(!this.isEmpty()){
            Node<Student> p = head;
            while( p != null ){
                if( p.data.getScode().equals(scode)){
                    result = p;
                    break;
                }
            }
        }
        return result;
    }
    
    public void deleteByScode(){
        String scode = inputScode();
        if(searchByScode(scode) != null){
            if(head.data.getScode().equals(scode)){
                Node p = head;
                head = head.next;
                p.next = null;
                return;
            }
            Node<Student> p = head;
            while( p.next != null ){
                if(p.next.data.getScode().equals(scode)){
                    Node temp = p.next;
                    p.next = p.next.next;
                    temp.next  = null;                    
                }
            }
        }
    }
        
}
