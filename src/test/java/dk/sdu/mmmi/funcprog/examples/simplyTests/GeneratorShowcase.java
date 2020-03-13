package dk.sdu.mmmi.funcprog.examples.simplyTests;

import org.junit.Test;
import org.quicktheories.core.DetatchedRandomnessSource;
import org.quicktheories.core.Gen;
import org.quicktheories.core.RandomnessSource;
import org.quicktheories.impl.Constraint;

import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.*;

public class GeneratorShowcase {
    @Test
    public void genTestNoStudentsOver169(){
    qt()
            .forAll(students()).check((student)-> student.height <= 169);

    }

    @Test
    public void addingTwoPositiveIntegersAlwaysGivesAPositiveInteger(){
        qt()
                .forAll(integers().allPositive()
                        , integers().allPositive())
                .check((i,j) -> i + j > 0);
    }

    private Gen<Student> students() {
        return names().zip(heights(),
                (name, height) -> new Student(name, height));
    }

    private Gen<String> names(){
        return strings().basicLatinAlphabet().ofLengthBetween(3,8);
    }
    private Gen<Integer> heights() {
        return integers().from(79).upToAndIncluding(170);
    }

    private class Student {
        private String name;
        private int height;
        public Student(String name, int height) {
            this.name = name;
            this.height = height;
        }

        @Override
        public String toString(){
          return "Height: "+ this.height + " name: " + this.name;
        }
    }
}