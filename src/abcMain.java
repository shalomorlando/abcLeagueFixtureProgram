import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.invoke.SwitchPoint;
import java.util.*;

import static com.sun.deploy.cache.Cache.exists;


public class abcMain {

    public static void main(String [] args) throws IOException {

        System.out.println("WELCOME TO THE ABC LEAGUE FIXTURE GENERATOR !!!");
        System.out.println("\n\n");
        System.out.println("Choose the function you would wish to execute:\n");
        List<Team> teams = null;


        boolean load= false;
        int choice=0;
        //String filePath = "";

        while(true)
        {
            System.out.println("\t 1. Import Data");
            //System.out.println("\t 2. Generate Match Fixtures");
            System.out.println("\t 2. Exit Program");

            Scanner input = new Scanner(System.in);
            choice = input.nextInt();
            switch (choice)
            {
                case 1:
                {
                    if (!load)
                    {
                        System.out.println("Enter the path to your desired file:(for example: teams.csv)\n");
                        Scanner input2 = new Scanner(System.in);
                        String filePath = input2.next();
                        System.out.println("\n");

                        teams = Generator.readTeamFromCSV(filePath);
                        if (teams.isEmpty())
                        {
                            System.out.println("INVALID INPUT");
                            break;
                        }
                        else
                        {
                            load = true;

                        }


                        do {
                            System.out.println("Your data has been loaded\n");
                            System.out.println("CHOOSE YOUR NEXT MOVE");
                            System.out.println("\t 1. Generate the fixtures\n");
                            System.out.println("\t 2.Display the fixtures\n");
                            //System.out.println("\t 3. Export fixtures to CSV file\n");

                            Scanner input3 = new Scanner(System.in);
                            choice = input3.nextInt();
                            switch(choice)
                            {
                                case 1:
                                {
                                    if (load == true)
                                    {

                                        List<Match> fixtures = Generator.generateFixture(teams);


                                        System.out.println("YOUR FIXTURE GENERATION IS COMPLETE WHAT IS YOUR NEXT MOVE:\n");
                                        /*for (Match f: fixtures)
                                        {
                                            System.out.println("WEEKEND: "+f.weekend+ "\nHOME: " + f.home.getTeamName() + "\nAWAY: "+ f.away.getTeamName() + "\nSTADIUM: " +f.stadium+"\n");
                                        }*/
                                        break;

                                    }
                                    else {
                                        System.out.println("Your file was not imported correctly");
                                        break;
                                    }
                                }
                                case 2:
                                {
                                  if (load == true)
                                  {
                                      teams = Generator.readTeamFromCSV(filePath);
                                      List<Match> fixtures = Generator.generateFixture(teams);
                                      for(Match match: fixtures)
                                      {
                                          System.out.println(match.p);
                                      }


                                      Collections.sort(fixtures, new Comparator<Match>() {
                                          @Override
                                          public int compare(Match o1, Match o2) {
                                              return  o1.p - o2.p;
                                          }
                                      });
                                      System.out.println("\nHERE ARE ALL THE FIXTURES\n");
                                      for (Match f: fixtures)
                                      {
                                          System.out.println("WEEKEND: "+f.weekend+ "\nHOME: " + f.home.getTeamName() + "\nAWAY: "+ f.away.getTeamName() + "\nSTADIUM: " +f.stadium+"\n");
                                      }
                                      String Choice = "";
                                      System.out.println("\nWould you wish to export the fixtures to a csv file");
                                      System.out.println("\n\t\t IF YES TYPE 'y' IF NO TYPE 'n'");
                                      Scanner input5 = new Scanner(System.in);
                                      Choice = input5.next();

                                      switch (Choice)
                                      {
                                          case "y":
                                          {
                                              //teams = Generator.readTeamFromCSV(filePath);
                                              //List<Match> fixtures = Generator.generateFixture(teams);
                                              System.out.println("YOU HAVE CHOSEN TO EXPORT THE FIXTURES KINDLY ENTER YOUR DESIRED  FILE NAME:");
                                              boolean exists = false;
                                              String fileName = "";
                                              Scanner input4 = new Scanner(System.in);
                                              fileName = input4.next();
                                              File file = new File("C:\\Users\\user\\Desktop\\school\\DSA\\abcLeague\\" + fileName);

                                              if (!file.exists()) {
                                                  file.createNewFile();
                                                  exists = true;
                                              }
                                              FileWriter csvWriter = new FileWriter(file, true);
                                              if (exists == true)
                                              {
                                                  csvWriter.append("Weekend");
                                                  csvWriter.append(",");
                                                  csvWriter.append("Home");
                                                  csvWriter.append(",");
                                                  csvWriter.append("Away");
                                                  csvWriter.append(",");
                                                  csvWriter.append("Stadium");
                                                  csvWriter.append(",");
                                                  csvWriter.append("Leg");
                                                  csvWriter.append(",");
                                                  csvWriter.append("HOME TEAM TOWN");
                                                  csvWriter.append(",");
                                                  csvWriter.append("AWAY TEAM TOWN");
                                                  csvWriter.append("\n");
                                              }

                                              for (Match fix : fixtures) {
                                                  csvWriter.append(String.join(",", fix.weekend + "," + fix.home.getTeamName() + "," + fix.away.getTeamName() + "," + fix.stadium + "," + fix.leg + "," + fix.home.getTeamTown() +
                                                          "," + fix.away.getTeamTown()));
                                                  csvWriter.append("\n");
                                              }

                                              csvWriter.flush();
                                              csvWriter.close();
                                              break;

                                          }
                                          case "n":
                                          {
                                              System.out.println("");
                                              break;
                                          }
                                          default:
                                          {
                                            break;
                                          }
                                      }
                                  }
                                }
                                default:
                                {
                                    break;
                                }
                            }
                        }while (choice!=2);

                    }

                }
                case 2:
                {
                    System.exit(0);
                }
                default:
                {
                    //System.out.println("INVALID CHOICE \n");
                    break;
                }
            }

        }

        //for(Match fixture: fixtures)
        //{

            //int weekend = fixture.weekend;

           // System.out.println(fixture);
        //}
    }
}
