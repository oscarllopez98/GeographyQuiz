package edu.uga.cs.geographyquiz;

/** Domain Class
 *POJO Class (Plain Old Java Object)
 * Represents a single question for a quiz*/
public class GeographyQuestion {

    //Private members
    private long id;
    private String continent;
    private String country;

    //Default Constructor
    public GeographyQuestion(){
        this.id = -1;
        this.continent = null;
        this.country = null;
    }

    //Overloaded Constructor w/ Parameters
    public GeographyQuestion(String continent, String country){
        this.id = -1;
        this.continent = continent;
        this.country = country;
    }

    public long getId() {
        return id;
    }

    public String getContinent() {
        return continent;
    }

    public String getCountry() {
        return country;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
