package asish.kdu.entities;

public class Team {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHomeGround() {
        return homeGround;
    }

    public void setHomeGround(String homeGround) {
        this.homeGround = homeGround;
    }

    public Team(String name, String homeGround) {
        this.name = name;
        this.homeGround = homeGround;
    }

    private String name;
    private String homeGround;

//    CSK,
//    SRH,
//    PBKS,
//    RCB,
//    MI,
//    RR,
//    DC,
//    KKR,

}
