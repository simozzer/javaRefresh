package je3.classes;

public class MailingAddress {

    String name;
    String streetAddress;
    String city;
    String state;
    String zipcode;

    public MailingAddress(String name, String streetAddress, String city, String state, String zipcode) {
        this.name = name;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append('\n').append(streetAddress).append('\n').append(city).append('/n').append(state).append('\n').append(zipcode);
        return sb.toString();
    }
}
