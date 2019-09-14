import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class Generator {


    public static List<Team> readTeamFromCSV(String fileName){
        List<Team> teams = new ArrayList<>();
        Path pathToFile = Paths.get("C:\\Users\\user\\Desktop\\school\\DSA\\abcLeague", fileName);

        //create instance of buffer reader
        try(BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)){
            //read first line from file
            String line = br.readLine();

            //loop till all lines are read
            while(line != null)
            {
                //use string.split to load a string array with the values
                //from each line of  the file, using comma as the delimeter

                String[] attributes = line.split(",");
                Team team = createTeam(attributes);

                //adding book into ArrayList
                teams.add(team);

                //read next line b4 looping
                //if end of file is reached, line would be null
                line = br.readLine();
            }

        }catch (IOException e)
        {
            e.printStackTrace();
        }
        return teams;
    }


    private static Team createTeam(String [] metadata){
        String TeamName = metadata[0];
        String  LocalTown = metadata[1];
        String TeamStadium = metadata[2];

        //create and return team of this metaData

        return new Team(TeamName, LocalTown, TeamStadium);
    }

    public static List<Match> generateFixture(List<Team> teams)
    {
        List<Match> fixtures=new ArrayList<>();
        List<Match> firstLegFixtures=new ArrayList<>();
        List<Match> derbiesF=new ArrayList<>();
        List<Match> secondLegFixtures=new ArrayList<>();

        List<Match> derbiesS=new ArrayList<>();




        int week = 1;

        for(Team t: teams)
        {
            for(Team t1: teams)
            {
                //check if team is match to its self

                if (t.getTeamName()!=(t1.getTeamName()))
                {
                    //if t1 has not been fixtured,
                    if(!t1.isFixtured())
                    {
                        //create fixture object
                        Match f= new Match();
                        f.home = t;
                        f.away = t1;
                        f.stadium = t.getTeamStadium();
                        f.isDerby = false;
                        f.leg = 1;
                        if (week %2 == 0)
                        {
                            f.weekend = week/2;
                        }

                        //check if derby
                        if (t.getTeamTown() == t1.getTeamTown())
                        {
                            f.isDerby = true;
                            f.p=0;
                            //Add fixture to first leg derbyList
                            derbiesF.add(f);
                            week++;
                            continue;
                        }else
                        {
                            f.p=1;
                            firstLegFixtures.add(f);
                            week++;
                        }
                    }

                    //second leg
                    if(!t1.isFixtured())
                    {
                        //create a fixture object
                        Match f = new Match();
                        //interchange home and away
                        f.home = t1;
                        f.away = t;
                        f.stadium = t1.getTeamStadium();
                        f.isDerby = false;
                        f.leg = 2;

                        if (week % 2==0)
                        {
                            f.weekend = week/2;
                        }
                        //check if is derby
                        if(t.getTeamTown()==t1.getTeamTown())
                        {
                            f.isDerby=true;
                            f.p=0;
                            //Add fixture to second derby list
                            derbiesS.add(f);
                            week++;
                        }else
                        {
                            f.p=1;
                            //add fixture to second fixture
                            secondLegFixtures.add(f);
                            week++;
                        }
                    }
                }

            }t.setFixture(true);
        }


        Collections.shuffle(firstLegFixtures);
        //Collections.shuffle(derbiesF);
        Collections.shuffle(secondLegFixtures);
        //Collections.shuffle(derbiesF);

        fixtures.addAll(firstLegFixtures);
        fixtures.addAll(derbiesF);

        fixtures.addAll(secondLegFixtures);
        fixtures.addAll(derbiesS);

        int i = 1;
        int j= 1;

        for (Match fixture: fixtures) {
            i++;

            if (i%2==0)
            {
                j=i;
            }
            fixture.weekend=j/2;

        }


        return fixtures;
    }


}
