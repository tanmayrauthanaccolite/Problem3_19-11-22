package Assignment_Java_19Nov.Problem_3;

import java.util.ArrayList;


public class Problem3 extends Thread {
    ArrayList<Fish> ar;
    int fishId;
    Problem3(ArrayList<Fish> ar)
    {
        this.ar=ar;
        fishId=1;
    }
    public boolean isNotDead(Fish f)
    {
        if(ar.contains(f))
            return true;
        return false;
    }
    public void pickFish() throws InterruptedException {
        while(true)
        {
            pick();
        }
    }
    public void pick() throws InterruptedException {
        Fish one;
        Fish two;
        if(ar.size()<=1){
            System.out.println("Only one/zero Fish Left");
            sleep(500);
            System.exit(0);
        }
        int pick1=(int)(Math.random()*ar.size());
        int pick2=(int)(Math.random()*ar.size());
        while(pick1==pick2)
        {
            pick1=(int)(Math.random()*ar.size());
            pick2=(int)(Math.random()*ar.size());
        }
        if(pick1>=ar.size()||pick2>=ar.size())
            return;
        int swap_temp=pick1;
        pick1=pick2;
        pick2=swap_temp;
        one=ar.get(pick1);
        two=ar.get(pick2);
        if(one!=null&&two!=null)
        {
            synchronized (one)
            {
                if(isNotDead(one)&&isNotDead(two)) {
                    synchronized (two)
                    {
                        ar.remove(one);
                        ar.remove(two);
                        if(one.gender==0&&two.gender==0)
                        {
                            System.out.println("Fish "+one.id+" and "+two.id+" killed each other");
                        }
                        else if(one.gender==1&&two.gender==1)
                        {
                            if(Math.random()>=0.5) {
                                ar.add(one);
                                System.out.println("Fish "+two.id+" died");
                            }
                            else {
                                ar.add(two);
                                System.out.println("Fish "+one.id+" died");
                            }
                        }
                        else{
                            Fish newFish1;
                            Fish newFish2;
                            newFish1=new Fish(fishId);
                            fishId++;
                            newFish2=new Fish(fishId);
                            fishId++;
                            ar.add(one);
                            ar.add(two);
                            ar.add(newFish1);
                            ar.add(newFish2);
                            System.out.println("Fish "+one.id+" and "+two.id+" formed two fishes "+newFish1.id+" and "+newFish2.id);
                        }
                        System.out.println("Total fishes are "+ar.size());
                    }
                }
            }
        }
    }
    public static void main(String [] args)
    {
        ArrayList<Fish> ar=new ArrayList<>();
        Problem3 p=new Problem3(ar);
        for(int i=0;i<10;i++)
        {
            Fish m=new Fish(p.fishId);
            p.fishId++;
            Fish f=new Fish(p.fishId);
            p.fishId++;
            p.ar.add(m);
            p.ar.add(f);
        }
        for(int i=0;i<5;i++)
        {
            new Thread(){
                public void run()
                {
                    try {
                        p.pickFish();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }.start();
        }
    }
}
