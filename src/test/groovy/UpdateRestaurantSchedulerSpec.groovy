import com.piwkosoft.foodFinder.Core.Facades.Interfaces.CountryFacade
import com.piwkosoft.foodFinder.Core.Facades.Interfaces.PlaceFacade
import com.piwkosoft.foodFinder.Core.Facades.Interfaces.PlaceTypeFacade
import com.piwkosoft.foodFinder.Dto.CountryDTO
import com.piwkosoft.foodFinder.Dto.PlaceTypeDTO
import com.piwkosoft.foodFinder.UpdateRestaurantScheduler
import com.piwkosoft.foodFinder.WebServices.CustomJson
import com.piwkosoft.foodFinder.WebServices.place.PlaceJson
import org.springframework.web.client.RestTemplate
import spock.lang.Specification
import spock.lang.Unroll

class UpdateRestaurantSchedulerSpec extends Specification {

    RestTemplate restTemplate = new RestTemplate()
    CountryFacade countryFacade = Mock()
    PlaceFacade placeFacade = Mock()
    PlaceTypeFacade placeTypeFacade = Mock()

    def "updateRestaurant count for page test for Wroclaw"() {
        given: "create country"
        def country = "Wroclaw"

        and: "create countries"
        CountryDTO countryDTO = CountryDTO
                .builder()
                .name(country)
                .build();

        and: "create list from countries"
        def countries = [countryDTO] as ArrayList

        and: "find countries"
        countryFacade.getAllCountries() >> countries

        and: "create place types"
        def placesIds = [1] as Set
        PlaceTypeDTO placeTypeDTO = PlaceTypeDTO
                .builder()
                .name("custom restaurant")
                .places(placesIds)
                .id(1)
                .build()
        def places = [placeTypeDTO] as List

        and:
        "find places" as List
        placeTypeFacade.findTypesByName(_) >> places

        and: "create json object"
        final CustomJson json = new PlaceJson(restTemplate)

        and: "create UpdateRestaurantScheduler"
        UpdateRestaurantScheduler updateRestaurantScheduler =
                new UpdateRestaurantScheduler(placeFacade, countryFacade, placeTypeFacade, restTemplate);

        when:
        def listOnPages = updateRestaurantScheduler.createAndPaging(String.format("%s%s", PlaceJson.BASE_URL, country), json)

        then:
        listOnPages.size() != 0
    }
}