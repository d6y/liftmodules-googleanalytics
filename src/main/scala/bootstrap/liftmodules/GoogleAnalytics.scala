/*
  Copyright 2011-2015 Spiral Arm Ltd

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.package bootstrap.liftmodules
*/
package bootstrap.liftmodules

import net.liftweb.http._
import net.liftweb.util.Props
import net.liftweb.http.js.JsCmd
import net.liftweb.http.js.JsCmds._
import net.liftweb.common.Logger
import net.liftmodules.googleanalytics.Async

object GoogleAnalytics {

  def init: Unit = init(()⇒true, Props.get("google.tag.manager.id", ""))

  /**
    * @param includeTest
    * @param gtmId - Google Tag Manager Id
    */
  def init(includeTest: () ⇒ Boolean, gtmId: String): Unit = {
    val js = Async.headJs(gtmId)
    val jsScript = Async.bodyScript(gtmId)
    def addTracking(s: LiftSession, r: Req): Unit = if (includeTest()) {
      S.putInHead(js)
      S.putAtEndOfBody(jsScript)
    }
    LiftSession.onBeginServicing = addTracking _ :: LiftSession.onBeginServicing
  }

  // noticeJs is by-name to allow you to side-effect (eg., set cookies)
  def alertUser(cond: () ⇒ Boolean)(noticeJs: ⇒ JsCmd): Unit = {

    def addNotice(s: LiftSession, r: Req) : Unit =  try {
        if (cond()) S.appendJs(noticeJs)
      } catch {
        case e : Throwable => Logger.apply("Unhandled exception from alertUser").error(e)
    }

    LiftSession.onBeginServicing = addNotice _ :: LiftSession.onBeginServicing
  }

  object dsl {
    object only {
      def when(f: => Boolean) = f _
    }
  }

}