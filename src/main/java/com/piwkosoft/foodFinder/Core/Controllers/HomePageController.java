package com.piwkosoft.foodFinder.Core.Controllers;

import com.piwkosoft.foodFinder.Core.Facades.Interfaces.PlaceFacade;
import com.piwkosoft.foodFinder.Core.Services.Interfaces.PlaceService;
import com.piwkosoft.foodFinder.Dto.PlaceDTO;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomePageController {

    private final static String PLACES = "places";

    private final PlaceFacade placeFacade;

    public HomePageController(final PlaceFacade placeFacade) {
        this.placeFacade = placeFacade;
    }

    @RequestMapping("/")
    public ModelAndView homePage() {
        final ModelAndView modelAndView = new ModelAndView("layout");

        final List<PlaceDTO> placeDTOS = Optional.ofNullable(placeFacade.findAllPlaces()).orElse(
            Collections.emptyList());

        modelAndView.addObject(PLACES, placeDTOS);

        return modelAndView;
    }
}