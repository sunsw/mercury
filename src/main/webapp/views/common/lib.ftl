<#macro layoutHead>
<head>
    <meta charset="utf-8">
    <title><@spring.message "system.title"/></title>
    <meta name="description" content="">
    <meta name="author" content="">

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

    <!-- #CSS Links -->
    <!-- Basic Styles -->
    <link rel="stylesheet" type="text/css" media="screen" href="/assets/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" media="screen" href="/assets/css/font-awesome.min.css">

    <!-- SmartAdmin Styles : Caution! DO NOT change the order -->
    <link rel="stylesheet" type="text/css" media="screen" href="/assets/css/smartadmin-production-plugins.min.css">
    <link rel="stylesheet" type="text/css" media="screen" href="/assets/css/smartadmin-production.min.css">
    <link rel="stylesheet" type="text/css" media="screen" href="/assets/css/smartadmin-skins.min.css">

    <!-- SmartAdmin RTL Support -->
    <link rel="stylesheet" type="text/css" media="screen" href="/assets/css/smartadmin-rtl.min.css">

    <!-- Demo purpose only: goes with demo.js, you can delete this css when designing your own WebApp -->
    <link rel="stylesheet" type="text/css" media="screen" href="/assets/css/mercury.min.css">

    <#nested />

    <!-- #FAVICONS -->
    <link rel="shortcut icon" href="/assets/img/favicon/favicon.ico" type="image/x-icon">
    <link rel="icon" href="/assets/img/favicon/favicon.ico" type="image/x-icon">

    <!-- #GOOGLE FONT -->
    <link rel="stylesheet" href="/assets/css/fonts.googleapis.css">

    <!-- #APP SCREEN / ICONS -->
    <!-- Specifying a Webpage Icon for Web Clip
         Ref: https://developer.apple.com/library/ios/documentation/AppleApplications/Reference/SafariWebContent/ConfiguringWebApplications/ConfiguringWebApplications.html -->
    <link rel="apple-touch-icon" href="/assets/img/splash/sptouch-icon-iphone.png">
    <link rel="apple-touch-icon" sizes="76x76" href="/assets/img/splash/touch-icon-ipad.png">
    <link rel="apple-touch-icon" sizes="120x120" href="/assets/img/splash/touch-icon-iphone-retina.png">
    <link rel="apple-touch-icon" sizes="152x152" href="/assets/img/splash/touch-icon-ipad-retina.png">

    <!-- iOS web-app metas : hides Safari UI Components and Changes Status Bar Appearance -->
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">

    <!-- Startup image for web apps -->
    <link rel="apple-touch-startup-image" href="/assets/img/splash/ipad-landscape.png"
          media="screen and (min-device-width: 481px) and (max-device-width: 1024px) and (orientation:landscape)">
    <link rel="apple-touch-startup-image" href="/assets/img/splash/ipad-portrait.png"
          media="screen and (min-device-width: 481px) and (max-device-width: 1024px) and (orientation:portrait)">
    <link rel="apple-touch-startup-image" href="/assets/img/splash/iphone.png"
          media="screen and (max-device-width: 320px)">
</head>
</#macro>

<#macro javascriptBase>
<!-- #PLUGINS -->
<!-- Link to CDN's jQuery + jQueryUI; fall back to local -->
<script src="//cdn.bootcss.com/jquery/2.2.2/jquery.min.js"></script>

<script src="//cdn.bootcss.com/jqueryui/1.12.1/jquery-ui.min.js"></script>

<#--sockjs-->
<script src="//cdn.bootcss.com/sockjs-client/1.1.1/sockjs.min.js"></script>

<!-- IMPORTANT: APP CONFIG -->
<script src="/assets/js/app.config.js"></script>

<!-- BOOTSTRAP JS -->
<script src="/assets/js/bootstrap/bootstrap.min.js"></script>

<!-- JQUERY VALIDATE -->
<script src="/assets/js/plugin/jquery-validate/jquery.validate.min.js"></script>

<!-- JQUERY MASKED INPUT -->
<script src="/assets/js/plugin/masked-input/jquery.maskedinput.min.js"></script>

<!-- MAIN APP JS FILE -->
<script src="/assets/js/app.min.js"></script>

<!--[if IE 8]>
    <h1>Your browser is out of date, please update your browser by going to www.microsoft.com/download</h1>
<![endif]-->
</#macro>

<#macro javascriptPlugin>
<!-- JS TOUCH : include this plugin for mobile drag / drop touch events-->
<script src="/assets/js/plugin/jquery-touch/jquery.ui.touch-punch.min.js"></script>

<!-- CUSTOM NOTIFICATION -->
<script src="/assets/js/notification/SmartNotification.min.js"></script>

<!-- JARVIS WIDGETS -->
<script src="/assets/js/smartwidgets/jarvis.widget.min.js"></script>

<!-- EASY PIE CHARTS -->
<script src="/assets/js/plugin/easy-pie-chart/jquery.easy-pie-chart.min.js"></script>

<!-- SPARKLINES -->
<script src="/assets/js/plugin/sparkline/jquery.sparkline.min.js"></script>

<!-- JQUERY SELECT2 INPUT -->
<script src="/assets/js/plugin/select2/select2.min.js"></script>

<!-- JQUERY UI + Bootstrap Slider -->
<script src="/assets/js/plugin/bootstrap-slider/bootstrap-slider.min.js"></script>

<!-- browser msie issue fix -->
<script src="/assets/js/plugin/msie-fix/jquery.mb.browser.min.js"></script>

<!-- FastClick: For mobile devices: you can disable this in app.js -->
<script src="/assets/js/plugin/fastclick/fastclick.min.js"></script>

<!-- Demo purpose only -->
<script src="/assets/js/mercury.min.js"></script>
</#macro>

<#macro javascriptSmartChatUI>
<!-- SmartChat UI : plugin -->
<script src="/assets/js/smart-chat-ui/smart.chat.ui.min.js"></script>
<script src="/assets/js/smart-chat-ui/smart.chat.manager.min.js"></script>
</#macro>

<#macro javascriptDatatable>
<script src="/assets/js/plugin/datatables/jquery.dataTables.min.js"></script>
<script src="/assets/js/plugin/datatables/dataTables.colVis.min.js"></script>
<script src="/assets/js/plugin/datatables/dataTables.tableTools.min.js"></script>
<script src="/assets/js/plugin/datatables/dataTables.bootstrap.min.js"></script>
<script src="/assets/js/plugin/datatable-responsive/datatables.responsive.min.js"></script>
<script src="/assets/js/plugin/bootstrapvalidator/bootstrapValidator.min.js"></script>
<script src="/app/js/mercury-datatable.js"></script>
</#macro>

<#macro menuTree menus>
    <#if menus?? && menus?size gt 0>
    <ul>
        <#list menus as menu>
            <li>
                <a href="${menu.menuUrl}">
                    <i class="
                    <#if menu.menuPic && menu.menuPic != ''>fa ${menu.menuPic}</#if>"></i> ${menu.menuTitle}
                </a>
                <@menuTree menus=menu.subMenus/>
            </li>
        </#list>
    </ul>
    </#if>
</#macro>
