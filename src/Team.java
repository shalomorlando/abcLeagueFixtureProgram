public class Team {

    private String TeamName;
    private String TeamTown;
    private String TeamStadium;
    private boolean fixture =false;

    public Team (String TeamName, String TeamTown, String TeamStadium) {
        this.TeamName = TeamName;
        this.TeamTown = TeamTown;
        this.TeamStadium = TeamStadium;

    }

    public boolean isFixtured()
    {
        return fixture;
    }
    public boolean setFixture(boolean fixture)
    {
        this.fixture = fixture;
        return fixture;
    }
    public String getTeamName(){

        return TeamName;
    }
    public void setTeamName(String TeamName){

        this.TeamName = TeamName;
    }

    public String getTeamStadium(){

        return TeamStadium;
    }
    public void setTeamStadiume(String TeamStadium){

        this.TeamStadium = TeamStadium;
    }

    public String getTeamTown(){
        return TeamTown;
    }
    public void setTeamTown(String TeamTown){

        this.TeamTown = TeamTown;
    }
    @Override
    public String toString()
    {
        return TeamName +","+ TeamTown+", "+TeamStadium;
    }

}
