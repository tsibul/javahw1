import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Cat {
    String name;
    String color;
    String breed;
    LocalDate birth;

    Cat(String n, String c, String b, LocalDate d){
        this.name = n;
        this.color = c;
        this.breed = b;
        this.birth = d;

    }

    Cat(){
        this.name = "Felix";
        this.color = "black";
        this.breed = "siam";
        this.birth = LocalDate.now();
    }

    @Override
    public boolean equals(Object obj) {
        if (this.getClass()!=obj.getClass()) return false;
        Cat compareCat = (Cat) obj;
        if (this.name.equals(compareCat.name) && this.color.equals(compareCat.color) &&
                this.breed.equals(compareCat.breed)) return true;
        return false;
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.name, this.color, this.breed );
//        return super.hashCode();
    }

    public void printCats(){
        System.out.println(this.name + " " + this.color + " " + this.breed + " " + this.birth);
    }
}
