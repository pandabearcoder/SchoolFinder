package eng.soft.schoolfinder.obj;

import java.util.ArrayList;

public class InitSchools {
    public ArrayList<SchoolObj> initItems() {
        ArrayList<SchoolObj> Schools = new ArrayList<>();
        SchoolObj school0 = new SchoolObj();
        school0.schoolID = 0;
        school0.schoolName = "Pacita Complex National HighSchool";
        school0.schoolAddress = "Pacita Complx 1, San Pedro, Laguna";
        school0.schoolTracks = "ABS, HUMMS, GAS";
        Schools.add(school0);

        SchoolObj school1 = new SchoolObj();
        school1.schoolID = 1;
        school1.schoolName = "Sampaguita National HighSchool";
        school1.schoolAddress = "Calendola, San Pedro, Laguna";
        school1.schoolTracks = "ABS, HUMMS, GAS, TVL";
        Schools.add(school1);

        return Schools;
    }
}
