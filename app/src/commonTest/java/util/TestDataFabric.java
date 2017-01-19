package util;

import com.mvpsmallproject.model.Name;
import com.mvpsmallproject.model.Profile;
import com.mvpsmallproject.model.Ribot;

import java.util.ArrayList;
import java.util.List;

public class TestDataFabric {

    public static List<Ribot> createRibotsList(){
        Profile profile = new Profile("0", "Jeff", new Name("Jeff@domain.com"));
        Ribot ribot = new Ribot(profile);
        List<Ribot> ribotsList = new ArrayList<>();
        ribotsList.add(ribot);
        return  ribotsList;
    }

}
