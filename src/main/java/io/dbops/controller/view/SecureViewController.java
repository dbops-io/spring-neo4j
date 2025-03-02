package io.dbops.controller.view;

import io.dbops.model.mapped.MappedProduct;
import io.dbops.service.ProductService;
import io.dbops.service.StatusService;
import io.dbops.service.UserService;
import io.dbops.util.DBOpsConstants;
import io.dbops.util.SessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/app")
public class SecureViewController extends DBOpsViewController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StatusService statusService;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;



    @RequestMapping(value = { "/home" }, method = RequestMethod.GET)
    public ModelAndView home(final HttpServletRequest req) {
        modelAndView = new ModelAndView("apphome");

        defaultModelAndViewObjects(req,"Home");
        return modelAndView;
    }

    @RequestMapping(value = { "/timeline" }, method = RequestMethod.GET)
    public ModelAndView timeline(final HttpServletRequest req) {
        modelAndView = new ModelAndView("timeline");

        modelAndView.addObject("statuses", statusService.getStatuses(SessionUtils.get(req, DBOpsConstants.userId).toString()));
        modelAndView.addObject("whoIsFollowingUser", userService.whoIsFollowingUser(SessionUtils.get(req, DBOpsConstants.userId).toString()));
        modelAndView.addObject("whoIsUserFollowing", userService.whoIsUserFollowing(SessionUtils.get(req, DBOpsConstants.userId).toString()));

        defaultModelAndViewObjects(req,"Social Timeline");
        return modelAndView;
    }

    @RequestMapping(value = { "/friendFinder" }, method = RequestMethod.GET)
    public ModelAndView friendFinder(final HttpServletRequest req) {
        modelAndView = new ModelAndView("friendFinder");
        modelAndView.addObject("whoIsFollowingUser", userService.whoIsFollowingUser(SessionUtils.get(req, DBOpsConstants.userId).toString()));
        modelAndView.addObject("whoIsUserFollowing", userService.whoIsUserFollowing(SessionUtils.get(req, DBOpsConstants.userId).toString()));
        modelAndView.addObject("potentialMatches", userService.potentialMatches(SessionUtils.get(req, DBOpsConstants.userId).toString()));
        defaultModelAndViewObjects(req,"Friend Finder");

        return modelAndView;
    }

    @RequestMapping(value = { "/profile" }, method = RequestMethod.GET)
    public ModelAndView profile(final HttpServletRequest req) {
        modelAndView = new ModelAndView("profile");

        defaultModelAndViewObjects(req,"Profile");
        return modelAndView;
    }

    @RequestMapping(value = { "/products" }, method = RequestMethod.GET)
    public ModelAndView products(final HttpServletRequest req) {
        modelAndView = new ModelAndView("products");
        //TODO remove during your build
        modelAndView.addObject("productId", "078510870X");
        //TODO remove during your build

        defaultModelAndViewObjects(req,"YAMAZON");
        return modelAndView;
    }

    @RequestMapping(value = { "/productSearch" }, method = RequestMethod.GET)
    public ModelAndView productSearch(final HttpServletRequest req) {
        modelAndView = new ModelAndView("productSearch");


        defaultModelAndViewObjects(req,"Product Search");
        return modelAndView;
    }

    @RequestMapping(value = { "/productdetail/{productId}" }, method = RequestMethod.GET)
    public ModelAndView productdetail(final HttpServletRequest req, @PathVariable String productId) {
        modelAndView = new ModelAndView("productdetail");
        MappedProduct result =productService.getProductForView(productId);
        modelAndView.addObject("result", result);
        defaultModelAndViewObjects(req,result.getProduct().getTitle());
        return modelAndView;
    }

    @RequestMapping(value = { "/productFinder" }, method = RequestMethod.GET)
    public ModelAndView productFinder(final HttpServletRequest req) {
        modelAndView = new ModelAndView("productFinder");

        defaultModelAndViewObjects(req,"Find a Product in an actual physical store");
        return modelAndView;
    }

    //TODO
    @RequestMapping(value = { "/console" }, method = RequestMethod.GET)
    public ModelAndView console(final HttpServletRequest req) {
        modelAndView = new ModelAndView("console");

        defaultModelAndViewObjects(req,"Console");
        return modelAndView;
    }
}
