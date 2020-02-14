package findagig.batch.infra;

import org.springframework.web.client.RestTemplate;

public class SongKickConsumer {

    public SongKickConsumer() {
    }


    public String getGigByVenueId(String venueId) {

        System.out.println("Getting venue by ID");

        try {
            final String uri = "https://api.songkick.com/api/3.0/venues/" + venueId + ".json?apikey=12345";

            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(uri, String.class);

            System.out.println(result);

            return "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
        }
        catch (Exception e) {
            System.out.println("ERROR: " + this.getClass().getName() + ". " + e.getStackTrace()[0].getMethodName()) ;
        }
        return null;
    }

}
