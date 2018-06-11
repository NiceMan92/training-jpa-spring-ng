package pattern.decorator.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pattern.decorator.LowerInputStreamDecorator;
import pattern.decorator.Person;
import pattern.decorator.Professor;
import pattern.decorator.Student;
import pattern.decorator.Toto;

public class LowerInputStreamDecoratorDriver {
    
    static void add(Person[] ps){
        System.out.println("Press me");
        ps[0] = new Student(30);
    }
    public static void main(String[] args) {

        Person[] pers = {new Student(3), new Professor(4)};
        Professor[] prof = {new Professor(3), new Professor(4)};
//        add(pers);
//        add(prof);
        pers = prof;
//        add(prof);
        List<Integer> liste = new ArrayList<Integer>();
        liste.add(1);
        liste.add(2);
        liste.add(3);
        System.out.println("Avant suppression : " + liste);
//        for (Integer integer : liste) {
//            liste.remove(integer);
//        }
        liste.removeAll(liste);
        
        System.out.println("Après suppression : " + liste);
        
        int i = Toto.methodeStatic();
        System.out.println("lecture du fichier copy.txt");
        try (LowerInputStreamDecorator lower = new LowerInputStreamDecorator(new FileInputStream("resources/copy.txt"))) {
            int c;
            while((c = lower.read()) != -1){
                System.out.print((char)c);
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
