package com.danielecampogiani;

import org.cybergarage.upnp.*;
import org.cybergarage.upnp.control.ActionListener;
import org.cybergarage.upnp.device.InvalidDescriptionException;

import java.io.File;


/**
 * Created by danielecampogiani on 04/06/14.
 */
public class MacDevice extends Device implements ActionListener {

    private StateVariable imagesVar;

    public MacDevice() throws InvalidDescriptionException {
        super(new File("description/description.xml"));

        Action getEchoAction = getAction("GetImages");
        getEchoAction.setActionListener(this);

        ServiceList serviceList = getServiceList();
        Service service = serviceList.getService(0);

        imagesVar = getStateVariable("ImagesList");

        setLeaseTime(60);
        start();
    }


    @Override
    public boolean actionControlReceived(Action action) {

        String actionName = action.getName();
        if (actionName.equals("GetImages")) {

            System.out.println("GetImages action control");

            File dir = new File("images");



            String[] images = dir.list();
            String result="";
            for (String current:images){
                System.out.println(current);
                result+=current+";";
            }

            Argument returnArg = action.getArgument("ReturnText");
            returnArg.setValue(result);
            return true;
        }
        return false;
    }
}
