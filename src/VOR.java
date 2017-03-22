/**
 * Created by ryanbarsatan on 3/21/17.
 */
public class VOR {

    public Integer xcord;
    public Integer ycord;
    public Double heading1;
    public Double heading2;
    public Double xheading;
    public Double yheading;
    public Double inflection;

    public Double degreelocation;
    public Integer direction;

    public VOR(Integer x, Integer y, Integer headingval){
        xcord = x;
        ycord = y;
        heading1 = headingval*1.0;
        heading2 = headingval+180.0;

        setPerpendicularHeading(); // set the two points of direction
        setDegreeLocation(); // sets the degree of the plane based on its location
        setDirection(); // set the direction of the airplane
        setInflection(); // set the inflection point for vor line
    }

    public void resetCoordinates(Integer x, Integer y, Integer headingval){
        xcord = x;
        ycord = y;
        heading1 = headingval*1.0;
        heading2 = headingval+180.0;

        setPerpendicularHeading(); // set the two points of direction
        setDegreeLocation(); // sets the degree of the plane based on its location
        setDirection(); // set the direction of the airplane
        setInflection(); // set the inflection point for vor line
    }

    //return the direction the plan is headed
    public String getDirection(){
        if(direction == 0){
            return "FROM";
        }
        else if(direction == 1){
            return "TO";
        }
        else if(direction == 3){
            return "ABEAM";
        }
        else{
            return "ERROR";
        }
    }

    // return the status of the signal
    public String getSignal(){
        if(direction != 3){
            return "GOOD";
        }
        else {
            return "BAD";
        }

    }


    // Set the headings perpendicular to the given heading
    public void setPerpendicularHeading(){
        if(heading1 < 90){
            xheading = heading1 + 90;
            yheading = heading1 + 270;
        }
        if(heading1 > 90){
            xheading = heading1 + 90;
            yheading = heading1 - 90;
        }
    }

    public void setDegreeLocation() {
        if (ycord < 0 && xcord > 0) { // if plane is in the 4th quadrant
            degreelocation = (90 - Math.abs(Math.toDegrees(Math.atan2(ycord, xcord)))) + 270;
        } else if (ycord < 0 && xcord < 0) { // if plane is in the 3rd quadrant
            degreelocation = (180 - Math.abs(Math.toDegrees(Math.atan2(ycord, xcord)))) + 180;
        } else { //if plane is in 1st or 2nd quadrant
            degreelocation = Math.abs(Math.toDegrees(Math.atan2(ycord, xcord)));
        }
    }


    // Set the direction of the airplane
    public void setDirection(){
        if(degreelocation < xheading-3 && degreelocation > yheading+3){
            direction = 0; // Airplane is going AWAY from station
        }
        else if(degreelocation > -xheading+3 && degreelocation < yheading-3){
            direction = 1; // Airplane is going TOWARD from station
        }
        else {
            direction = 3; // Airplane is ABEAM to station (within 3 degrees of 0 or 180)
        }
    }

    public void setInflection(){
        if(direction == 0){ // if we are in quadrant 1 or 2
            if(heading1 < degreelocation){
                inflection = degreelocation - heading1;
            }
            else {
                inflection = heading1 + degreelocation;
            }
        }
        else if(direction == 1){ // if we acre in quadrant 3 or 4
            if(heading2 < degreelocation){
                inflection = heading2 - (degreelocation - heading2);
            }
            else {
                inflection = heading2 + (heading2 - degreelocation);
            }
        }
    }





}
