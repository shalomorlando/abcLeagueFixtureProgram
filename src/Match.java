public class Match {

    int weekend;
    Team away;
    Team home;
    int leg;
    boolean isDerby;
    public String stadium;
    int p = 0;

    @Override
    public String toString()
    {
        return  weekend+""+away+", "+home+", "+ leg+ ", "+ stadium;
    }



}
