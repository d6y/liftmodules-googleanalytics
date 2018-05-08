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
package net.liftmodules.googleanalytics

import scala.xml.Unparsed

object Async {

  def headJs(id: String) = {
    val tagManagerContent: String =
      "(function(w,d,s,l,i){w[l]=w[l]||[];w[l].push({'gtm.start':\n"+
        "new Date().getTime(),event:'gtm.js'});var f=d.getElementsByTagName(s)[0],\n"+
        "j=d.createElement(s),dl=l!='dataLayer'?'&l='+l:'';j.async=true;j.src=\n"+
        "'https://www.googletagmanager.com/gtm.js?id='+i+dl;f.parentNode.insertBefore(j,f);\n"+
        "})(window,document,'script','dataLayer','"+id+"');"

    <script>
      <!-- Google Tag Manager -->
      { tagManagerContent }
      <!-- End Google Tag Manager -->
    </script>
  }

  def bodyScript(id: String) = {
    val url = s"https://www.googletagmanager.com/ns.html?id=$id"

    <noscript>
      <iframe src={url} height="0" width="0"
              style="display:none;visibility:hidden">
      </iframe>
    </noscript>
  }

}