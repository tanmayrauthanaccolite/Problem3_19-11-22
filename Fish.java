package Assignment_Java_19Nov.Problem_3;

public class Fish {
    int gender;

    int id;
    Fish(int id)
    {
        gender=Math.random()>=0.5?1:0;
        this.id=id;
    }
    Fish(int gender,int id)
    {
        this.gender=gender;
        this.id=id;
    }
}
