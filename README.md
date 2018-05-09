# Google Analytics Lift Module

[![Build Status](https://travis-ci.org/d6y/liftmodules-googleanalytics.svg?branch=master)](https://travis-ci.org/d6y/liftmodules-googleanalytics)

Inserts the [Google Analytics async tracking code](http://code.google.com/apis/analytics/docs/tracking/asyncTracking.html) into a [Lift](http://www.liftweb.net) application.

That is, if you supply a Google Analytics tracking ID, the head of all pages will have the Google tracking Javascript code automatically added.


## Using this module

1. Include the dependency:

        // For Lift 3.2.x (Scala 2.12):
        "net.liftmodules" %% "google-analytics_3.2" % "1.2.0"

    Note that from 1.2.0 onwards this module switched to using Google Tag Manager (via [#8](https://github.com/d6y/liftmodules-googleanalytics/pull/8)).
    The versions below use the older `ga.js` approach.

        // For Lift 3.1.x (Scala 2.12):
        "net.liftmodules" %% "google-analytics_3.1" % "1.1.0-SNAPSHOT"

        // For Lift 3.0.x (Scala 2.12, 2.11):
        "net.liftmodules" %% "google-analytics_3.0" % "1.1"

        // For Lift 3.0.x (Scala 2.10):
        "net.liftmodules" %% "google-analytics_3.0" % "1.0-SNAPSHOT"

        // For Lift 2.6.x (Scala 2.11):
        "net.liftmodules" %% "google-analytics_2.6" % "1.1-SNAPSHOT"

        // For Lift 2.6.x (Scala 2.9 and 2.10):
        "net.liftmodules" %% "google-analytics_2.6" % "1.0"

        // For Lift 2.5.x (Scala 2.9 and 2.10):
         "net.liftmodules" %% "google-analytics_2.5" % "1.0"


2. In your application's Boot.boot code:

          bootstrap.liftmodules.GoogleAnalytics.init

3. Finally, set your tracking code as the `google.tag.manager.id` in your Props file.  For example, add the following to `src/main/resources/production.default.props`

         google.tag.manager.id=GTM-XXXXXX

    ...obviously replacing `XXXXXX` with the code Google issued you with.  Be sure to start your Lift app with `-Drun.mode=production` flag (or set the value of google.tag.manager.id in your dev props file).


## Conditional behaviour

To selectively control if analytics is enable, provide a `()=>Boolean` function to the `init` method:

    import bootstrap.liftmodules.GoogleAnalytics

    GoogleAnalytics.init { () => S.cookieValue("cookie_consent") isDefined }

There's some sugar if you want it:

    import bootstrap.liftmodules.GoogleAnalytics
    import GoogleAnalytics.dsl._

    GoogleAnalytics.init {
     only when S.cookieValue("ckns_policy").isDefined
    }


## End user cookie notification

To assist towards compliance to the EU Privacy and Electronic Communications Regulations (cookie laws) this module includes an end-user notification trigger.  It may not be right for you, and it is your responsibility to review you compliance with any regulation.

    GoogleAnalytics.alertUser ( only when S.cookieValue("cookie_consent").isEmpty ) {
      JsAlert("We set Cookies")
    }

The `alertUser` method expects a test of type `()=>Boolean` and then a `()=>JsCmd`.


## Development

When working on this module, if you want to see the effect in a Lift project without having to publish the module, you can change your Lift project to remove the dependency on the published module and instread add a local dependency.  E.g.,

    // project/LoalModuleDev.scala
    import sbt._
    object LocalModuleDev extends Build {
      lazy val root = Project("", file(".")) dependsOn(google)
      lazy val google = ProjectRef(uri("../liftmodules-googleanalytics"), "LiftModule")
    }

When you build your Lift project, SBT will automatically compile changes in the local Google Analytics module.

