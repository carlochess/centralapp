package com.greenbug.carlos.Moddle;

public class prueba {
    static String html = "<!DOCTYPE html>\n" +
            "<html  dir=\"ltr\" lang=\"es\" xml:lang=\"es\">\n" +
            "<head>\n" +
            "    <title>Campus Virtual - Universidad del Valle</title>\n" +
            "    <link rel=\"shortcut icon\" href=\"https://campusvirtual.univalle.edu.co/moodle/theme/image.php/leatherbound/theme/1414765834/favicon\" />\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, maximum-scale=1.0\" />\n" +
            "    <meta name=\"description\" content=\"Bienvenidos al Campus Virtual Univalle\n" +
            "La Universidad del Valle pone a disposición de sus docentes y estudiantes esta valiosa herramienta denominada Campus Virtual Univalle bajo la plataforma Moodle. Con esta herramienta se facilitarán los procesos de enseñanza aprendizaje en las asignaturas que ofrece la institución, en sus distintas modalidades educativas, tanto para los Programas de Pregrado y Posgrado.\n" +
            " La Universidad les presenta un cálido saludo y los invita a conocer y a familiarizarse con esta potente herramienta.\n" +
            " \" />\n" +
            "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
            "<meta name=\"keywords\" content=\"moodle, Campus Virtual - Universidad del Valle\" />\n" +
            "<meta http-equiv=\"pragma\" content=\"no-cache\" />\n" +
            "<meta http-equiv=\"expires\" content=\"0\" />\n" +
            "<script type=\"text/javascript\">\n" +
            "//<![CDATA[\n" +
            "var M = {}; M.yui = {};\n" +
            "M.pageloadstarttime = new Date();\n" +
            "M.cfg = {\"wwwroot\":\"https:\\/\\/campusvirtual.univalle.edu.co\\/moodle\",\"sesskey\":\"vlp1UrX593\",\"loadingicon\":\"https:\\/\\/campusvirtual.univalle.edu.co\\/moodle\\/theme\\/image.php\\/leatherbound\\/core\\/1414765834\\/i\\/loading_small\",\"themerev\":\"1414765834\",\"slasharguments\":1,\"theme\":\"leatherbound\",\"jsrev\":\"1414765834\",\"svgicons\":true};var yui1ConfigFn = function(me) {if(/-skin|reset|fonts|grids|base/.test(me.name)){me.type='css';me.path=me.path.replace(/\\.js/,'.css');me.path=me.path.replace(/\\/yui2-skin/,'/assets/skins/sam/yui2-skin')}};\n" +
            "var yui2ConfigFn = function(me) {var parts=me.name.replace(/^moodle-/,'').split('-'),component=parts.shift(),module=parts[0],min='-min';if(/-(skin|core)$/.test(me.name)){parts.pop();me.type='css';min=''};if(module){var filename=parts.join('-');me.path=component+'/'+module+'/'+filename+min+'.'+me.type}else me.path=component+'/'+component+'.'+me.type};\n" +
            "YUI_config = {\"debug\":false,\"base\":\"https:\\/\\/campusvirtual.univalle.edu.co\\/moodle\\/lib\\/yuilib\\/3.13.0\\/\",\"comboBase\":\"https:\\/\\/campusvirtual.univalle.edu.co\\/moodle\\/theme\\/yui_combo.php?\",\"combine\":true,\"filter\":null,\"insertBefore\":\"firstthemesheet\",\"groups\":{\"yui2\":{\"base\":\"https:\\/\\/campusvirtual.univalle.edu.co\\/moodle\\/lib\\/yuilib\\/2in3\\/2.9.0\\/build\\/\",\"comboBase\":\"https:\\/\\/campusvirtual.univalle.edu.co\\/moodle\\/theme\\/yui_combo.php?\",\"combine\":true,\"ext\":false,\"root\":\"2in3\\/2.9.0\\/build\\/\",\"patterns\":{\"yui2-\":{\"group\":\"yui2\",\"configFn\":yui1ConfigFn}}},\"moodle\":{\"name\":\"moodle\",\"base\":\"https:\\/\\/campusvirtual.univalle.edu.co\\/moodle\\/theme\\/yui_combo.php?m\\/1414765834\\/\",\"combine\":true,\"comboBase\":\"https:\\/\\/campusvirtual.univalle.edu.co\\/moodle\\/theme\\/yui_combo.php?\",\"ext\":false,\"root\":\"m\\/1414765834\\/\",\"patterns\":{\"moodle-\":{\"group\":\"moodle\",\"configFn\":yui2ConfigFn}},\"filter\":null,\"modules\":{\"moodle-core-formautosubmit\":{\"requires\":[\"base\",\"event-key\"]},\"moodle-core-dock\":{\"requires\":[\"base\",\"node\",\"event-custom\",\"event-mouseenter\",\"event-resize\",\"escape\",\"moodle-core-dock-loader\"]},\"moodle-core-dock-loader\":{\"requires\":[\"escape\"]},\"moodle-core-formchangechecker\":{\"requires\":[\"base\",\"event-focus\"]},\"moodle-core-chooserdialogue\":{\"requires\":[\"base\",\"panel\",\"moodle-core-notification\"]},\"moodle-core-notification\":{\"requires\":[\"moodle-core-notification-dialogue\",\"moodle-core-notification-alert\",\"moodle-core-notification-confirm\",\"moodle-core-notification-exception\",\"moodle-core-notification-ajaxexception\"]},\"moodle-core-notification-dialogue\":{\"requires\":[\"base\",\"node\",\"panel\",\"event-key\",\"dd-plugin\",\"moodle-core-lockscroll\"]},\"moodle-core-notification-alert\":{\"requires\":[\"moodle-core-notification-dialogue\"]},\"moodle-core-notification-confirm\":{\"requires\":[\"moodle-core-notification-dialogue\"]},\"moodle-core-notification-exception\":{\"requires\":[\"moodle-core-notification-dialogue\"]},\"moodle-core-notification-ajaxexception\":{\"requires\":[\"moodle-core-notification-dialogue\"]},\"moodle-core-lockscroll\":{\"requires\":[\"plugin\",\"base-build\"]},\"moodle-core-tooltip\":{\"requires\":[\"base\",\"node\",\"io-base\",\"moodle-core-notification\",\"json-parse\",\"widget-position\",\"widget-position-align\",\"event-outside\",\"cache-base\"]},\"moodle-core-popuphelp\":{\"requires\":[\"moodle-core-tooltip\"]},\"moodle-core-blocks\":{\"requires\":[\"base\",\"node\",\"io\",\"dom\",\"dd\",\"dd-scroll\",\"moodle-core-dragdrop\",\"moodle-core-notification\"]},\"moodle-core-actionmenu\":{\"requires\":[\"base\",\"event\",\"node-event-simulate\"]},\"moodle-calendar-eventmanager\":{\"requires\":[\"base\",\"node\",\"event-mouseenter\",\"overlay\",\"moodle-calendar-eventmanager-skin\"]},\"moodle-course-management\":{\"requires\":[\"base\",\"node\",\"io-base\",\"moodle-core-notification-exception\",\"json-parse\",\"dd-constrain\",\"dd-proxy\",\"dd-drop\",\"dd-delegate\",\"node-event-delegate\"]},\"moodle-course-util\":{\"requires\":[\"node\"],\"use\":[\"moodle-course-util-base\"],\"submodules\":{\"moodle-course-util-base\":{},\"moodle-course-util-section\":{\"requires\":[\"node\",\"moodle-course-util-base\"]},\"moodle-course-util-cm\":{\"requires\":[\"node\",\"moodle-course-util-base\"]}}},\"moodle-course-categoryexpander\":{\"requires\":[\"node\",\"event-key\"]},\"moodle-mod_quiz-autosave\":{\"requires\":[\"base\",\"node\",\"event\",\"event-valuechange\",\"node-event-delegate\",\"io-form\"]},\"moodle-block_messageteacher-form\":{\"requires\":[\"base\",\"io\",\"io-form\",\"node\",\"json\",\"moodle-core-notification\"]},\"moodle-block_navigation-navigation\":{\"requires\":[\"base\",\"io-base\",\"node\",\"event-synthetic\",\"event-delegate\",\"json-parse\"]},\"moodle-filter_glossary-autolinker\":{\"requires\":[\"base\",\"node\",\"io-base\",\"json-parse\",\"event-delegate\",\"overlay\",\"moodle-core-notification-alert\"]},\"moodle-tool_capability-search\":{\"requires\":[\"base\",\"node\"]},\"moodle-theme_bootstrapbase-bootstrap\":{\"requires\":[\"node\",\"selector-css3\"]},\"moodle-assignfeedback_editpdf-editor\":{\"requires\":[\"base\",\"event\",\"node\",\"io\",\"graphics\",\"json\",\"event-move\",\"querystring-stringify-simple\",\"moodle-core-notification-dialog\",\"moodle-core-notification-exception\",\"moodle-core-notification-ajaxexception\"]}}}},\"modules\":{\"core_filepicker\":{\"name\":\"core_filepicker\",\"fullpath\":\"https:\\/\\/campusvirtual.univalle.edu.co\\/moodle\\/lib\\/javascript.php\\/1414765834\\/repository\\/filepicker.js\",\"requires\":[\"base\",\"node\",\"node-event-simulate\",\"json\",\"async-queue\",\"io-base\",\"io-upload-iframe\",\"io-form\",\"yui2-treeview\",\"panel\",\"cookie\",\"datatable\",\"datatable-sort\",\"resize-plugin\",\"dd-plugin\",\"escape\",\"moodle-core_filepicker\"]},\"block_simple_clock\":{\"name\":\"block_simple_clock\",\"fullpath\":\"https:\\/\\/campusvirtual.univalle.edu.co\\/moodle\\/lib\\/javascript.php\\/1414765834\\/blocks\\/simple_clock\\/module.js\",\"requires\":[]}}};\n" +
            "M.yui.loader = {modules: {}};\n" +
            "\n" +
            "//]]>\n" +
            "</script>\n" +
            "<link rel=\"stylesheet\" type=\"text/css\" href=\"https://campusvirtual.univalle.edu.co/moodle/theme/yui_combo.php?3.13.0/cssreset/cssreset-min.css&amp;3.13.0/cssfonts/cssfonts-min.css&amp;3.13.0/cssgrids/cssgrids-min.css&amp;3.13.0/cssbase/cssbase-min.css\" /><link rel=\"stylesheet\" type=\"text/css\" href=\"https://campusvirtual.univalle.edu.co/moodle/theme/yui_combo.php?rollup/3.13.0/yui-moodlesimple-min.css\" /><script type=\"text/javascript\" src=\"https://campusvirtual.univalle.edu.co/moodle/theme/yui_combo.php?rollup/3.13.0/yui-moodlesimple-min.js\"></script><script type=\"text/javascript\" src=\"https://campusvirtual.univalle.edu.co/moodle/theme/yui_combo.php?rollup/1414765834/mcore-min.js\"></script><script id=\"firstthemesheet\" type=\"text/css\">/** Required in order to fix style inclusion problems in IE with YUI **/</script><link rel=\"stylesheet\" type=\"text/css\" href=\"https://campusvirtual.univalle.edu.co/moodle/theme/styles.php/leatherbound/1414765834/all\" />\n" +
            "<script type=\"text/javascript\" src=\"https://campusvirtual.univalle.edu.co/moodle/lib/javascript.php/1414765834/lib/javascript-static.js\"></script>\n" +
            "<script type=\"text/javascript\" src=\"https://campusvirtual.univalle.edu.co/moodle/theme/javascript.php/leatherbound/1414765834/head\"></script>\n" +
            "\n" +
            "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
            "\n" +
            "		<!-- Always force latest IE rendering engine (even in intranet) & Chrome Frame\n" +
            "		Remove this if you use the .htaccess -->\n" +
            "		<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\" />\n" +
            "		<link rel=\"stylesheet\" type=\"text/css\" href=\"../css/header.css\"/>\n" +
            "		<title>Campus Virtual-Universidad del Valle</title>\n" +
            "		<meta name=\"description\" content=\"Bienvenidos al Campus Virtual Univalle\n" +
            "		La Universidad del Valle pone a disposición de sus docentes y estudiantes esta valiosa herramienta denominada Campus Virtual Univalle bajo la plataforma Moodle. Con esta herramienta se facilitarán los procesos de enseñanza aprendizaje en las asignaturas que ofrece la institución, en sus distintas modalidades educativas, tanto para los Programas de Pregrado y Posgrado.\n" +
            "		La Universidad les presenta un cálido saludo y los invita a conocer y a familiarizarse con esta potente herramienta.\" />\n" +
            "		<meta name=\"author\" content=\"DINTEV\"/>\n" +
            "              <meta name=\"keywords\" content=\"cursos educacion virtual aprendizaje e-learning campus \" />\n" +
            "               <meta name = \"robáis\" content=\"All\" />\n" +
            "               <meta name=\"Distribution\" content=\"Global\" />\n" +
            "<script type=\"text/javascript\">\n" +
            "\n" +
            "  var _gaq = _gaq || [];\n" +
            "  _gaq.push(['_setAccount', 'UA-4692832-6']);\n" +
            "  _gaq.push(['_trackPageview']);\n" +
            "\n" +
            "  (function() {\n" +
            "    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;\n" +
            "    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';\n" +
            "    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);\n" +
            "  })();\n" +
            "\n" +
            "</script>\n" +
            "\n" +
            "  <script>\n" +
            "  $(function() {\n" +
            "    $( \".block_course_list .content .column #accordion\" ).accordion({\n" +
            "      heightStyle: \"content\"\n" +
            "    });\n" +
            "  });\n" +
            "</script>\n" +
            "<script type=\"text/javascript\"\n" +
            "src=\"https://campusvirtual.univalle.edu.co/moodle/lib/mathjax/MathJax.js?config=TeX-AMS-MML_HTMLorMML\">\n" +
            "</script>\n" +
            "<script type=\"text/x-mathjax-config\">\n" +
            "  MathJax.Hub.Config({\n" +
            "    extensions: [\"tex2jax.js\"],\n" +
            "    jax: [\"input/TeX\", \"output/HTML-CSS\"],\n" +
            "    tex2jax: {\n" +
            "      inlineMath: [ ['$$','$$'], [\"\\\\[\",\"\\\\]\"] ],\n" +
            "      displayMath: [ [\"\\\\[\",\"\\\\]\"] ],\n" +
            "      processEscapes: true\n" +
            "    },\n" +
            "    \"HTML-CSS\": { availableFonts: [\"TeX\"] }\n" +
            "  });\n" +
            "</script>\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "    \n" +
            "        <script type=\"text/javascript\">\n" +
            "    YUI().use('node', function(Y) {\n" +
            "        window.thisisy = Y;\n" +
            "    	Y.one(window).on('scroll', function(e) {\n" +
            "    	    var node = Y.one('#back-to-top');\n" +
            "\n" +
            "    	    if (Y.one('window').get('docScrollY') > Y.one('#page-content-wrapper').getY()) {\n" +
            "    		    node.setStyle('display', 'block');\n" +
            "    	    } else {\n" +
            "    		    node.setStyle('display', 'none');\n" +
            "    	    }\n" +
            "    	});\n" +
            "\n" +
            "    });\n" +
            "    </script>\n" +
            "</head>\n" +
            "\n" +
            "<body id=\"page-site-index\" class=\"format-site course path-site dir-ltr lang-es yui-skin-sam yui3-skin-sam campusvirtual-univalle-edu-co--moodle pagelayout-frontpage course-1 context-34 \">\n" +
            "<div class=\"skiplinks\"><a class=\"skip\" href=\"#maincontent\">Saltar a contenido principal</a></div>\n" +
            "<script type=\"text/javascript\">\n" +
            "//<![CDATA[\n" +
            "document.body.className += ' jsenabled';\n" +
            "//]]>\n" +
            "</script>\n" +
            "\n" +
            "\n" +
            "<div id=\"page\">\n" +
            "\n" +
            "<!-- START OF HEADER -->\n" +
            "    <div id=\"page-header\">\n" +
            "	<div id=\"page-header-wrapper\" class=\"wrapper clearfix\">\n" +
            "						  <a href=\"http://www.univalle.edu.co/\">\n" +
            "                                                     <div id=\"logosimbolo\"> </div>\n" +
            "						  </a>\n" +
            "\n" +
            "						<div id=\"login\">\n" +
            "						<div class=\"profilepic\" id=\"profilepic\">\n" +
            "										<a href=\"https://campusvirtual.univalle.edu.co/moodle/user/view.php?id=52096&amp;course=1\">\n" +
            "										<img src=\"https://campusvirtual.univalle.edu.co/moodle/user/pix.php?file=/52096/f1.jpg\"/>\n" +
            "										</a>\n" +
            "								</div>\n" +
            "								<div class=\"profilename\" id=\"profilename\">\n" +
            "								<div class=\"logininfo\">Usted se ha identificado como <a href=\"https://campusvirtual.univalle.edu.co/moodle/user/profile.php?id=52096\" title=\"Ver perfil\">CARLOS ROMAN PALACIOS</a> (<a href=\"https://campusvirtual.univalle.edu.co/moodle/login/logout.php?sesskey=vlp1UrX593\">Salir</a>)</div>\n" +
            "								</div>\n" +
            "						</div>                <div id=\"menu_horizontal\">\n" +
            "                 <ul class=\"menu_horizontal\">\n" +
            "                   <li><a href=\"http://www.univalle.edu.co/busquedas.html\" target=\"_blank\" class=\"menu_horizontal\">Buscar</a></li>\n" +
            "                   <li><a href=\"http://www.univalle.edu.co/directorio/\" target=\"_blank\" class=\"menu_horizontal\">Directorio</a></li>\n" +
            "                   <li><a href=\"http://biblioteca.univalle.edu.co/\" target=\"_blank\" class=\"menu_horizontal\">Biblioteca</a></li>\n" +
            "                   <li class=\"idioma\"> <div class=\"langmenu\"><form method=\"get\" action=\"https://campusvirtual.univalle.edu.co/moodle/\" id=\"single_select_f54c40838e880416\"><div><label for=\"single_select54c40838e880417\"><span class=\"accesshide \" >Idioma</span></label><select id=\"single_select54c40838e880417\" class=\"select autosubmit langmenu\" name=\"lang\"><option value=\"de\">Deutsch (de)</option><option value=\"en\">English (en)</option><option selected=\"selected\" value=\"es\">Español - Internacional (es)</option><option value=\"fr\">Français (fr)</option><option value=\"it\">Italiano (it)</option><option value=\"pt\">Português - Portugal (pt)</option></select><noscript class=\"inline\"><div><input type=\"submit\" value=\"Ir\" /></div></noscript></div></form></div></li>\n" +
            "                 </ul>\n" +
            "                </div>\n" +
            "                \n" +
            "               \n" +
            "            \n" +
            "            </div>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</div>\n" +
            "\n" +
            "<!-- END OF HEADER -->\n" +
            "\n" +
            "\n" +
            "<!-- START OF CONTENT -->\n" +
            "\n" +
            "<div id=\"page-content-wrapper\" class=\"wrapper clearfix\">\n" +
            "\n" +
            "\n" +
            "    <div id=\"space\">\n" +
            "    </div>\n" +
            "\n" +
            "    <div id=\"page-content\">\n" +
            "        <div id=\"region-main-box\">\n" +
            "            <div id=\"region-post-box\">\n" +
            "\n" +
            "                <div id=\"region-main-wrap\">\n" +
            "                    <div id=\"region-main\">\n" +
            "                        <div class=\"region-content\">\n" +
            "                            <h1 class=\"page_tittle\">Campus Virtual</h1>\n" +
            "                                                        \n" +
            "                            <div role=\"main\"><span id=\"maincontent\"></span><a href=\"#skipsitenews\" class=\"skip-block\">Saltar novedades</a><div id=\"site-news-forum\"><h2>Novedades</h2><div class=\"subscribelink\"><a href=\"https://campusvirtual.univalle.edu.co/moodle/mod/forum/subscribe.php?id=1&amp;sesskey=vlp1UrX593\">Suscribirse a este foro</a></div><a id=\"p100133\"></a><div class=\"forumpost clearfix firstpost starter\" role=\"region\" aria-label=\"Persistencia de cursos en el Campus Virtual by MORALES SANCHEZ DORIS\"><div class=\"row header clearfix\"><div class=\"left picture\"><a href=\"https://campusvirtual.univalle.edu.co/moodle/user/profile.php?id=2\"><img src=\"https://campusvirtual.univalle.edu.co/moodle/pluginfile.php/38575/user/icon/leatherbound/f2?rev=2237737\" alt=\"Imagen de MORALES SANCHEZ DORIS\" title=\"Imagen de MORALES SANCHEZ DORIS\" class=\"userpicture\" width=\"35\" height=\"35\" /></a></div><div class=\"topic firstpost starter\"><div class=\"subject\" role=\"heading\" aria-level=\"2\">Persistencia de cursos en el Campus Virtual</div><div class=\"author\" role=\"heading\" aria-level=\"2\">de <a href=\"https://campusvirtual.univalle.edu.co/moodle/user/view.php?id=2&amp;course=1\">MORALES SANCHEZ DORIS</a> - Monday, 19 de January de 2015, 16:35</div></div></div><div class=\"row maincontent clearfix\"><div class=\"left\"><div class=\"grouppictures\">&nbsp;</div></div><div class=\"no-overflow\"><div class=\"content\"><div class=\"posting fullpost\"><div style=\"font-family:arial, sans-serif;font-size:13px;text-align:justify;\">Los cursos inscritos en el Campus Virtual se conservan hasta que se cumple cualquiera de estas dos condiciones:</div>\n" +
            "<div style=\"font-family:arial, sans-serif;font-size:13px;text-align:justify;\"> </div>\n" +
            "<div style=\"font-family:arial, sans-serif;font-size:13px;text-align:justify;\">\n" +
            "<p>1. Que sea un curso sin estudiantes matriculados y que no haya tenido actividad en el último año.</p>\n" +
            "<p>2. Por solicitud expresa del profesor del curso o del Director de la Unidad Académica en ausencia definitiva del profesor.</p>\n" +
            "<div style=\"font-family:arial, sans-serif;font-size:13px;text-align:justify;\"> </div>\n" +
            "</div>\n" +
            "<div style=\"font-family:arial, sans-serif;font-size:13px;text-align:justify;\">Invitamos a todos los profesores a que evalúen la pertinencia de mantener sus cursos antiguos en el Campus Virtual y a que a través de este <strong><em><a title=\"Eliminación de cursos en el Campus Virtual\" href=\"https://docs.google.com/forms/d/1uWvd3NuJ1-2i0vqR0WuzlvM9I9COT3Szas4H9a3pr4o/viewform?usp=send_form\" target=\"_blank\">formulario</a></em></strong> soliciten la eliminación definitiva de los cursos para liberar espacio en la plataforma.</div><div class=\"attachedimages\"></div></div></div></div></div><div class=\"row side\"><div class=\"left\">&nbsp;</div><div class=\"options clearfix\"><div class=\"commands\"></div></div></div></div><a id=\"p99888\"></a><div class=\"forumpost clearfix firstpost starter\" role=\"region\" aria-label=\"Inscripción de Cursos en el Campus Virtual Univalle by MORALES SANCHEZ DORIS\"><div class=\"row header clearfix\"><div class=\"left picture\"><a href=\"https://campusvirtual.univalle.edu.co/moodle/user/profile.php?id=2\"><img src=\"https://campusvirtual.univalle.edu.co/moodle/pluginfile.php/38575/user/icon/leatherbound/f2?rev=2237737\" alt=\"Imagen de MORALES SANCHEZ DORIS\" title=\"Imagen de MORALES SANCHEZ DORIS\" class=\"userpicture\" width=\"35\" height=\"35\" /></a></div><div class=\"topic firstpost starter\"><div class=\"subject\" role=\"heading\" aria-level=\"2\">Inscripción de Cursos en el Campus Virtual Univalle</div><div class=\"author\" role=\"heading\" aria-level=\"2\">de <a href=\"https://campusvirtual.univalle.edu.co/moodle/user/view.php?id=2&amp;course=1\">MORALES SANCHEZ DORIS</a> - Monday, 19 de January de 2015, 14:35</div></div></div><div class=\"row maincontent clearfix\"><div class=\"left\"><div class=\"grouppictures\">&nbsp;</div></div><div class=\"no-overflow\"><div class=\"content\"><div class=\"posting shortenedpost\"><div style=\"text-align:center;\"><span style=\"font-size:large;\"><strong><span style=\"font-size:medium;\">Instrucciones para la inscripción de los cursos en el Campus Virtual de la Universidad del Valle</span><br /></strong></span></div>\n" +
            "<div style=\"text-align:center;\"> </div>\n" +
            "<div>\n" +
            "<ul><li style=\"text-align:justify;\"><span style=\"font-size:small;\"><strong>Cursos que están registrados en el Sistema de Registro Académico</strong></span><span style=\"font-size:x-small;\">   (<em><strong>Por favor lea completamente las instrucciones antes de hacer clic sobre cualquier enlace</strong></em>)</span></li>\n" +
            "</ul></div>\n" +
            "<div style=\"text-align:justify;\"><ol><li style=\"text-align:justify;\">Hacer clic en el enlace <a title=\"Inscripción de cursos en el Campus Virtual\" href=\"https://campusvirtual.univalle.edu.co/InterfazRegistroACampus/\" target=\"_blank\">Interfaz ...</a></li></ol></div><a href=\"https://campusvirtual.univalle.edu.co/moodle/mod/forum/discuss.php?d=51144\">Ver el resto del tema</a><div class=\"post-word-count\">(367 palabras)</div></div></div></div></div><div class=\"row side\"><div class=\"left\">&nbsp;</div><div class=\"options clearfix\"><div class=\"commands\"></div></div></div></div><a id=\"p100624\"></a><div class=\"forumpost clearfix firstpost starter\" role=\"region\" aria-label=\"Formato de solicitud para los Talleres sobre el Campus Virtual by MORALES SANCHEZ DORIS\"><div class=\"row header clearfix\"><div class=\"left picture\"><a href=\"https://campusvirtual.univalle.edu.co/moodle/user/profile.php?id=2\"><img src=\"https://campusvirtual.univalle.edu.co/moodle/pluginfile.php/38575/user/icon/leatherbound/f2?rev=2237737\" alt=\"Imagen de MORALES SANCHEZ DORIS\" title=\"Imagen de MORALES SANCHEZ DORIS\" class=\"userpicture\" width=\"35\" height=\"35\" /></a></div><div class=\"topic firstpost starter\"><div class=\"subject\" role=\"heading\" aria-level=\"2\">Formato de solicitud para los Talleres sobre el Campus Virtual</div><div class=\"author\" role=\"heading\" aria-level=\"2\">de <a href=\"https://campusvirtual.univalle.edu.co/moodle/user/view.php?id=2&amp;course=1\">MORALES SANCHEZ DORIS</a> - Thursday, 13 de November de 2014, 11:07</div></div></div><div class=\"row maincontent clearfix\"><div class=\"left\"><div class=\"grouppictures\">&nbsp;</div></div><div class=\"no-overflow\"><div class=\"content\"><div class=\"posting fullpost\"><p style=\"text-align:justify;\">Estimado profesor, por favor diligenciar el <a title=\"Capacitación personal para profesores\" href=\"https://docs.google.com/a/correounivalle.edu.co/forms/d/1EUGYFrAYQ6vVuQitH6CUcMNUlfNq6GznX_2KzES0Wdc/viewform\">formulario de solicitud</a> para la solicitud de capacitación sobre el uso de la Plataforma del Campus Virtual, indicándonos el horario, el tipo de taller, etc.</p><div class=\"attachedimages\"></div></div></div></div></div><div class=\"row side\"><div class=\"left\">&nbsp;</div><div class=\"options clearfix\"><div class=\"commands\"></div></div></div></div><div class=\"forumolddiscuss\"><a href=\"https://campusvirtual.univalle.edu.co/moodle/mod/forum/view.php?f=1&amp;showall=1\">Temas antiguos</a> ...</div></div><span class=\"skip-block-to\" id=\"skipsitenews\"></span><br /></div>                        </div>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "\n" +
            "                                <div id=\"region-pre\" class=\"block-region\">\n" +
            "                    <div class=\"region-content\">\n" +
            "                        <a href=\"#sb-1\" class=\"skip-block\">Saltar Administración</a><div id=\"inst142756\" class=\"block_settings  block\" role=\"navigation\" data-block=\"settings\" data-instanceid=\"142756\" aria-labelledby=\"instance-142756-header\" data-dockable=\"1\"><div class=\"header\"><div class=\"title\"><div class=\"block_action\"></div><h2 id=\"instance-142756-header\">Administración</h2></div></div><div class=\"content\"><div id=\"settingsnav\" class=\"box block_tree_box\"><ul class=\"block_tree list\"><li class=\"type_unknown collapsed contains_branch\" aria-expanded=\"false\"><p class=\"tree_item branch root_node\" id=\"usersettings\"><span tabindex=\"0\">Ajustes de mi perfil</span></p><ul><li class=\"type_setting collapsed item_with_icon\"><p class=\"tree_item leaf\"><a href=\"https://campusvirtual.univalle.edu.co/moodle/user/edit.php?id=52096&amp;course=1\"><img alt=\"\" class=\"smallicon navicon\" title=\"\" src=\"https://campusvirtual.univalle.edu.co/moodle/theme/image.php/leatherbound/core/1414765834/i/navigationitem\" />Editar perfil</a></p></li>\n" +
            "<li class=\"type_setting collapsed item_with_icon\"><p class=\"tree_item leaf\"><a href=\"https://campusvirtual.univalle.edu.co/moodle/login/change_password.php?id=1\"><img alt=\"\" class=\"smallicon navicon\" title=\"\" src=\"https://campusvirtual.univalle.edu.co/moodle/theme/image.php/leatherbound/core/1414765834/i/navigationitem\" />Cambiar contraseña</a></p></li>\n" +
            "<li class=\"type_setting collapsed item_with_icon\"><p class=\"tree_item leaf\"><a href=\"https://campusvirtual.univalle.edu.co/moodle/user/managetoken.php?sesskey=vlp1UrX593\"><img alt=\"\" class=\"smallicon navicon\" title=\"\" src=\"https://campusvirtual.univalle.edu.co/moodle/theme/image.php/leatherbound/core/1414765834/i/navigationitem\" />Claves de seguridad</a></p></li>\n" +
            "<li class=\"type_setting collapsed item_with_icon\"><p class=\"tree_item leaf\"><a href=\"https://campusvirtual.univalle.edu.co/moodle/message/edit.php?id=52096\"><img alt=\"\" class=\"smallicon navicon\" title=\"\" src=\"https://campusvirtual.univalle.edu.co/moodle/theme/image.php/leatherbound/core/1414765834/i/navigationitem\" />Mensajería</a></p></li>\n" +
            "<li class=\"type_unknown collapsed contains_branch\" aria-expanded=\"false\"><p class=\"tree_item branch\"><span tabindex=\"0\">Blogs</span></p><ul><li class=\"type_setting collapsed item_with_icon\"><p class=\"tree_item leaf\"><a href=\"https://campusvirtual.univalle.edu.co/moodle/blog/preferences.php\"><img alt=\"\" class=\"smallicon navicon\" title=\"\" src=\"https://campusvirtual.univalle.edu.co/moodle/theme/image.php/leatherbound/core/1414765834/i/navigationitem\" />Preferencias</a></p></li>\n" +
            "<li class=\"type_setting collapsed item_with_icon\"><p class=\"tree_item leaf\"><a href=\"https://campusvirtual.univalle.edu.co/moodle/blog/external_blogs.php\"><img alt=\"\" class=\"smallicon navicon\" title=\"\" src=\"https://campusvirtual.univalle.edu.co/moodle/theme/image.php/leatherbound/core/1414765834/i/navigationitem\" />Blogs externos</a></p></li>\n" +
            "<li class=\"type_setting collapsed item_with_icon\"><p class=\"tree_item leaf\"><a href=\"https://campusvirtual.univalle.edu.co/moodle/blog/external_blog_edit.php\"><img alt=\"\" class=\"smallicon navicon\" title=\"\" src=\"https://campusvirtual.univalle.edu.co/moodle/theme/image.php/leatherbound/core/1414765834/i/navigationitem\" />Registrar un blog externo</a></p></li></ul></li>\n" +
            "<li class=\"type_unknown collapsed contains_branch\" aria-expanded=\"false\"><p class=\"tree_item branch\"><span tabindex=\"0\">Insignias</span></p><ul><li class=\"type_setting collapsed item_with_icon\"><p class=\"tree_item leaf\"><a href=\"https://campusvirtual.univalle.edu.co/moodle/badges/preferences.php\"><img alt=\"\" class=\"smallicon navicon\" title=\"\" src=\"https://campusvirtual.univalle.edu.co/moodle/theme/image.php/leatherbound/core/1414765834/i/navigationitem\" />Preferencias</a></p></li>\n" +
            "<li class=\"type_setting collapsed item_with_icon\"><p class=\"tree_item leaf\"><a href=\"https://campusvirtual.univalle.edu.co/moodle/badges/mybackpack.php\"><img alt=\"\" class=\"smallicon navicon\" title=\"\" src=\"https://campusvirtual.univalle.edu.co/moodle/theme/image.php/leatherbound/core/1414765834/i/navigationitem\" />Configuración de la mochila</a></p></li></ul></li></ul></li></ul></div></div></div><span id=\"sb-1\" class=\"skip-block-to\"></span><a href=\"#sb-2\" class=\"skip-block\">Saltar Mis cursos</a><div id=\"inst142770\" class=\"block_course_list  block list_block\" role=\"navigation\" data-block=\"course_list\" data-instanceid=\"142770\" aria-labelledby=\"instance-142770-header\" data-dockable=\"1\"><div class=\"header\"><div class=\"title\"><div class=\"block_action\"></div><h2 id=\"instance-142770-header\">Mis cursos</h2></div></div><div class=\"content\"><ul class=\"unlist\"><li class=\"r0\"><div class=\"column c1\"><div id=\"accordion\"><h3 class=\"course_tittle_block\">Segundo Semestre de 2014</h3><div><ul class=\"grupo_semestre\"><li><img src=\"https://campusvirtual.univalle.edu.co/moodle/theme/image.php/leatherbound/core/1414765834/i/course\" class=\"icon\" alt=\"\" /><a  title=\"00-761130M-01-201408041\" href=\"https://campusvirtual.univalle.edu.co/moodle/course/view.php?id=20366\" class=\"course_item\">PROBABILIDAD Y ESTADÍSTICA-01</a></li><li><img src=\"https://campusvirtual.univalle.edu.co/moodle/theme/image.php/leatherbound/core/1414765834/i/course\" class=\"icon\" alt=\"\" /><a  title=\"00-730107M-02-201408041\" href=\"https://campusvirtual.univalle.edu.co/moodle/course/view.php?id=20325\" class=\"course_item\">INTRODUCCIÓN A LA GESTIÓN AMBIENTAL-02</a></li><li><img src=\"https://campusvirtual.univalle.edu.co/moodle/theme/image.php/leatherbound/core/1414765834/i/course\" class=\"icon\" alt=\"\" /><a  title=\"00-750022M-80-201408041\" href=\"https://campusvirtual.univalle.edu.co/moodle/course/view.php?id=20450\" class=\"course_item\">SISTEMAS DE INFORMACIÓN I-80</a></li><li><img src=\"https://campusvirtual.univalle.edu.co/moodle/theme/image.php/leatherbound/core/1414765834/i/course\" class=\"icon\" alt=\"\" /><a  title=\"00-750090M-01-201408041\" href=\"https://campusvirtual.univalle.edu.co/moodle/course/view.php?id=20209\" class=\"course_item\">COMPLEJIDAD Y OPTIMIZACIÓN-01</a></li><li><img src=\"https://campusvirtual.univalle.edu.co/moodle/theme/image.php/leatherbound/core/1414765834/i/course\" class=\"icon\" alt=\"\" /><a  title=\"00-750085M-01-201408041\" href=\"https://campusvirtual.univalle.edu.co/moodle/course/view.php?id=20143\" class=\"course_item\">PROGRAMACIÓN INTERACTIVA-01</a></li><li><img src=\"https://campusvirtual.univalle.edu.co/moodle/theme/image.php/leatherbound/core/1414765834/i/course\" class=\"icon\" alt=\"\" /><a  title=\"00-750087M-01-201408041\" href=\"https://campusvirtual.univalle.edu.co/moodle/course/view.php?id=20117\" class=\"course_item\">APLICACIONES EN EL WEB Y REDES INALÁMBRICAS-01</a></li><li><img src=\"https://campusvirtual.univalle.edu.co/moodle/theme/image.php/leatherbound/core/1414765834/i/course\" class=\"icon\" alt=\"\" /><a  title=\"00-750114M-01-201408041\" href=\"https://campusvirtual.univalle.edu.co/moodle/course/view.php?id=19948\" class=\"course_item\">INTRODUCCIÓN DE LAS PLATAFORMAS DE COMPUTACIÓN A GRAN ESCALA-01</a></li><li><img src=\"https://campusvirtual.univalle.edu.co/moodle/theme/image.php/leatherbound/core/1414765834/i/course\" class=\"icon\" alt=\"\" /><a  title=\"00-750097M-01-201408041\" href=\"https://campusvirtual.univalle.edu.co/moodle/course/view.php?id=19447\" class=\"course_item\">SEMINARIO DE PROYECTO DE GRADO-01</a></li></ul></div><h3 class=\"course_tittle_block\">Primer Semestre de 2014</h3><div><ul class=\"grupo_semestre\"><li><img src=\"https://campusvirtual.univalle.edu.co/moodle/theme/image.php/leatherbound/core/1414765834/i/course\" class=\"icon\" alt=\"\" /><a  title=\"00-761130M-01-201402041\" href=\"https://campusvirtual.univalle.edu.co/moodle/course/view.php?id=18204\" class=\"course_item\">PROBABILIDAD Y ESTADÍSTICA-01</a></li><li><img src=\"https://campusvirtual.univalle.edu.co/moodle/theme/image.php/leatherbound/core/1414765834/i/course\" class=\"icon\" alt=\"\" /><a  title=\"00-750096M-80-201402041\" href=\"https://campusvirtual.univalle.edu.co/moodle/course/view.php?id=18562\" class=\"course_item\">FUNDAMENTOS DE REDES-80</a></li><li><img src=\"https://campusvirtual.univalle.edu.co/moodle/theme/image.php/leatherbound/core/1414765834/i/course\" class=\"icon\" alt=\"\" /><a  title=\"00-750092M-01-201402041\" href=\"https://campusvirtual.univalle.edu.co/moodle/course/view.php?id=18153\" class=\"course_item\">DESARROLLO DE SOFTWARE II-01</a></li><li><img src=\"https://campusvirtual.univalle.edu.co/moodle/theme/image.php/leatherbound/core/1414765834/i/course\" class=\"icon\" alt=\"\" /><a  title=\"00-750033M-01-201402041\" href=\"https://campusvirtual.univalle.edu.co/moodle/course/view.php?id=18149\" class=\"course_item\">INTRODUCCIÓN A LA INTELIGENCIA ARTIFICIAL-01</a></li><li><img src=\"https://campusvirtual.univalle.edu.co/moodle/theme/image.php/leatherbound/core/1414765834/i/course\" class=\"icon\" alt=\"\" /><a  title=\"00-750006M-01-201402041\" href=\"https://campusvirtual.univalle.edu.co/moodle/course/view.php?id=18136\" class=\"course_item\">COMPUTACIÓN GRÁFICA-01</a></li></ul></div><h3 class=\"course_tittle_block\">Segundo Semestre de 2013</h3><div><ul class=\"grupo_semestre\"><li><img src=\"https://campusvirtual.univalle.edu.co/moodle/theme/image.php/leatherbound/core/1414765834/i/course\" class=\"icon\" alt=\"\" /><a  title=\"00-750095M-01-201308041\" href=\"https://campusvirtual.univalle.edu.co/moodle/course/view.php?id=16503\" class=\"course_item\">FUNDAMENTOS DE LENGUAJES DE PROGRAMACIÓN-01</a></li><li><img src=\"https://campusvirtual.univalle.edu.co/moodle/theme/image.php/leatherbound/core/1414765834/i/course\" class=\"icon\" alt=\"\" /><a  title=\"00-750008M-80-201308041\" href=\"https://campusvirtual.univalle.edu.co/moodle/course/view.php?id=16483\" class=\"course_item\">SISTEMAS OPERATIVOS-80</a></li><li><img src=\"https://campusvirtual.univalle.edu.co/moodle/theme/image.php/leatherbound/core/1414765834/i/course\" class=\"icon\" alt=\"\" /><a  title=\"00-750091M-01-201308041\" href=\"https://campusvirtual.univalle.edu.co/moodle/course/view.php?id=16153\" class=\"course_item\">DESARROLLO DE SOFTWARE I-01</a></li><li><img src=\"https://campusvirtual.univalle.edu.co/moodle/theme/image.php/leatherbound/core/1414765834/i/course\" class=\"icon\" alt=\"\" /><a  title=\"00-750030M-01-201308041\" href=\"https://campusvirtual.univalle.edu.co/moodle/course/view.php?id=15794\" class=\"course_item\">BASES DE DATOS I-01</a></li><li><img src=\"https://campusvirtual.univalle.edu.co/moodle/theme/image.php/leatherbound/core/1414765834/i/course\" class=\"icon\" alt=\"\" /><a  title=\"00-750030M-80-201308041\" href=\"https://campusvirtual.univalle.edu.co/moodle/course/view.php?id=15793\" class=\"course_item\">BASES DE DATOS I-80</a></li></ul></div><h3 class=\"course_tittle_block\">Primer Semestre de 2013</h3><div><ul class=\"grupo_semestre\"><li><img src=\"https://campusvirtual.univalle.edu.co/moodle/theme/image.php/leatherbound/core/1414765834/i/course\" class=\"icon\" alt=\"\" /><a  title=\"00-750086M-01-201302041\" href=\"https://campusvirtual.univalle.edu.co/moodle/course/view.php?id=14619\" class=\"course_item\">ANÁLISIS Y MÉTODOS NUMÉRICOS-01</a></li><li><img src=\"https://campusvirtual.univalle.edu.co/moodle/theme/image.php/leatherbound/core/1414765834/i/course\" class=\"icon\" alt=\"\" /><a  title=\"00-750094M-01-201302041\" href=\"https://campusvirtual.univalle.edu.co/moodle/course/view.php?id=14153\" class=\"course_item\">FUNDAMENTOS DE ANÁLISIS Y DISEÑO DE ALGORITMOS-01</a></li></ul></div><h3 class=\"course_tittle_block\">Segundo Semestre de 2012</h3><div><ul class=\"grupo_semestre\"><li><img src=\"https://campusvirtual.univalle.edu.co/moodle/theme/image.php/leatherbound/core/1414765834/i/course\" class=\"icon\" alt=\"\" /><a  title=\"00-750084M-80-201208041\" href=\"https://campusvirtual.univalle.edu.co/moodle/course/view.php?id=13552\" class=\"course_item\">MATEMÁTICAS DISCRETAS II-80</a></li></ul></div><h3 class=\"course_tittle_block\">Primer Semestre de 2012</h3><div><ul class=\"grupo_semestre\"><li><img src=\"https://campusvirtual.univalle.edu.co/moodle/theme/image.php/leatherbound/core/1414765834/i/course\" class=\"icon\" alt=\"\" /><a  title=\"00-750085M-01-201202041\" href=\"https://campusvirtual.univalle.edu.co/moodle/course/view.php?id=11104\" class=\"course_item\">PROGRAMACIÓN INTERACTIVA-01</a></li><li><img src=\"https://campusvirtual.univalle.edu.co/moodle/theme/image.php/leatherbound/core/1414765834/i/course\" class=\"icon\" alt=\"\" /><a  title=\"00-750084M-01-201202041\" href=\"https://campusvirtual.univalle.edu.co/moodle/course/view.php?id=11337\" class=\"course_item\">MATEMÁTICAS DISCRETAS II-01</a></li><li><img src=\"https://campusvirtual.univalle.edu.co/moodle/theme/image.php/leatherbound/core/1414765834/i/course\" class=\"icon\" alt=\"\" /><a  title=\"00-111052M-01-201202041\" href=\"https://campusvirtual.univalle.edu.co/moodle/course/view.php?id=11106\" class=\"course_item\">CÁLCULO III-01</a></li></ul></div><h3 class=\"course_tittle_block\">Segundo Semestre de 2011</h3><div><ul class=\"grupo_semestre\"><li><img src=\"https://campusvirtual.univalle.edu.co/moodle/theme/image.php/leatherbound/core/1414765834/i/course\" class=\"icon\" alt=\"\" /><a  title=\"00-750081M-01-201108041\" href=\"https://campusvirtual.univalle.edu.co/moodle/course/view.php?id=9889\" class=\"course_item\">INTRODUCCIÓN A LA PROGRAMACIÓN ORIENTADA A OBJETOS-01</a></li><li><img src=\"https://campusvirtual.univalle.edu.co/moodle/theme/image.php/leatherbound/core/1414765834/i/course\" class=\"icon\" alt=\"\" /><a  title=\"00-750083M-01-201108041\" href=\"https://campusvirtual.univalle.edu.co/moodle/course/view.php?id=9794\" class=\"course_item\">MATEMÁTICAS DISCRETAS I-01</a></li><li><img src=\"https://campusvirtual.univalle.edu.co/moodle/theme/image.php/leatherbound/core/1414765834/i/course\" class=\"icon\" alt=\"\" /><a  title=\"00-111048M-05-201108041\" href=\"https://campusvirtual.univalle.edu.co/moodle/course/view.php?id=9679\" class=\"course_item\">ÁLGEBRA LINEAL-05</a></li></ul></div><h3 class=\"course_tittle_block\">Primer Semestre de 2011</h3><div><ul class=\"grupo_semestre\"><li><img src=\"https://campusvirtual.univalle.edu.co/moodle/theme/image.php/leatherbound/core/1414765834/i/course\" class=\"icon\" alt=\"\" /><a  title=\"00-204140M-30-201102041\" href=\"https://campusvirtual.univalle.edu.co/moodle/course/view.php?id=8208\" class=\"course_item\">LECTURA TEXTOS ACADÉMICOS EN INGLÉS III-30</a></li><li><img src=\"https://campusvirtual.univalle.edu.co/moodle/theme/image.php/leatherbound/core/1414765834/i/course\" class=\"icon\" alt=\"\" /><a  title=\"00-750060M-01-201102041\" href=\"https://campusvirtual.univalle.edu.co/moodle/course/view.php?id=8233\" class=\"course_item\">INTRODUCCIÓN A LA INGENIERÍA DE SISTEMAS-01</a></li><li><img src=\"https://campusvirtual.univalle.edu.co/moodle/theme/image.php/leatherbound/core/1414765834/i/course\" class=\"icon\" alt=\"\" /><a  title=\"00-750082M-07-201102041\" href=\"https://campusvirtual.univalle.edu.co/moodle/course/view.php?id=8474\" class=\"course_item\">INTRODUCCIÓN A LA TECNOLOGÍA INFORMÁTICA-07</a></li><li><img src=\"https://campusvirtual.univalle.edu.co/moodle/theme/image.php/leatherbound/core/1414765834/i/course\" class=\"icon\" alt=\"\" /><a  title=\"00-750080M-01-201102041\" href=\"https://campusvirtual.univalle.edu.co/moodle/course/view.php?id=8629\" class=\"course_item\">FUNDAMENTOS DE PROGRAMACIÓN-01</a></li></ul></div><h3 class=\"course_tittle_block\">No Regulares</h3><div><ul class=\"grupo_semestre\"><li><img src=\"https://campusvirtual.univalle.edu.co/moodle/theme/image.php/leatherbound/core/1414765834/i/course\" class=\"icon\" alt=\"\" /><a  title=\"ANÁLISIS DE LA PROPUESTA DE REFORMA A LA EDUCACIÓN SUPERIOR\" href=\"https://campusvirtual.univalle.edu.co/moodle/course/view.php?id=9385\" class=\"course_item\">ANÁLISIS DE LA PROPUESTA DE REFORMA A LA EDUCACIÓN SUPERIOR</a></li></ul></div></div></li></ul><div class=\"footer\"><a href=\"https://campusvirtual.univalle.edu.co/moodle/course/index.php\">Todos los cursos</a> ...</div></div></div><span id=\"sb-2\" class=\"skip-block-to\"></span><a href=\"#sb-3\" class=\"skip-block\">Saltar Bibliotecas</a><div id=\"inst2717\" class=\"block_html  block\" role=\"complementary\" data-block=\"html\" data-instanceid=\"2717\" aria-labelledby=\"instance-2717-header\" data-dockable=\"1\"><div class=\"header\"><div class=\"title\"><div class=\"block_action\"></div><h2 id=\"instance-2717-header\">Bibliotecas</h2></div></div><div class=\"content\"><div class=\"no-overflow\"><ul>\n" +
            "<li class=\"libro_azul\"><a title=\"Biblioteca Universidad del Valle\" href=\"http://biblioteca.univalle.edu.co/\">División de Bibliotecas</a></li>\n" +
            "<li class=\"libro_azul\"><a title=\"Biblioteca Digital Univalle\" href=\"http://bibliotecadigital.univalle.edu.co/\">Biblioteca Digital</a></li>\n" +
            "</ul></div></div></div><span id=\"sb-3\" class=\"skip-block-to\"></span><a href=\"#sb-4\" class=\"skip-block\">Saltar Menú principal</a><div id=\"inst164544\" class=\"block_site_main_menu  block list_block\" role=\"complementary\" data-block=\"site_main_menu\" data-instanceid=\"164544\" aria-labelledby=\"instance-164544-header\" data-dockable=\"1\"><div class=\"header\"><div class=\"title\"><div class=\"block_action\"></div><h2 id=\"instance-164544-header\">Menú principal</h2></div></div><div class=\"content\"><ul class=\"unlist\"><li class=\"r0\"><div class=\"column c1\"><a title=\"Foros\"   href=\"https://campusvirtual.univalle.edu.co/moodle/mod/forum/view.php?id=1\"><img src=\"https://campusvirtual.univalle.edu.co/moodle/theme/image.php/leatherbound/forum/1414765834/icon\" class=\"icon\" alt=\"\" />Novedades</a></div></li>\n" +
            "<li class=\"r1\"><div class=\"column c1\"><a title=\"Archivos\"   href=\"https://campusvirtual.univalle.edu.co/moodle/mod/resource/view.php?id=321049\"><img src=\"https://campusvirtual.univalle.edu.co/moodle/theme/image.php/leatherbound/core/1414765834/f/html-24\" class=\"icon\" alt=\"\" />Manual Básico sobre el Campus Virtual</a></div></li>\n" +
            "<li class=\"r0\"><div class=\"column c1\"><a title=\"Páginas\"   href=\"https://campusvirtual.univalle.edu.co/moodle/mod/page/view.php?id=973\"><img src=\"https://campusvirtual.univalle.edu.co/moodle/theme/image.php/leatherbound/page/1414765834/icon\" class=\"icon\" alt=\"\" />Inscripción de Cursos en el Campus Virtual</a></div></li>\n" +
            "<li class=\"r1\"><div class=\"column c1\"><a title=\"Páginas\"   href=\"https://campusvirtual.univalle.edu.co/moodle/mod/page/view.php?id=157004\"><img src=\"https://campusvirtual.univalle.edu.co/moodle/theme/image.php/leatherbound/page/1414765834/icon\" class=\"icon\" alt=\"\" />Ingreso al Campus Virtual Univalle</a></div></li>\n" +
            "<li class=\"r0\"><div class=\"column c1\"><a title=\"Páginas\"   href=\"https://campusvirtual.univalle.edu.co/moodle/mod/page/view.php?id=407125\"><img src=\"https://campusvirtual.univalle.edu.co/moodle/theme/image.php/leatherbound/page/1414765834/icon\" class=\"icon\" alt=\"\" />Solicitud Capacitación en el Campus Virtual</a></div></li></ul></div></div><span id=\"sb-4\" class=\"skip-block-to\"></span>                    </div>\n" +
            "                </div>\n" +
            "                \n" +
            "                                <div id=\"region-post\" class=\"block-region\">\n" +
            "                    <div class=\"region-content\">\n" +
            "                        <a href=\"#sb-6\" class=\"skip-block\">Saltar Reloj</a><div id=\"inst67730\" class=\"block_simple_clock  block\" role=\"complementary\" data-block=\"simple_clock\" data-instanceid=\"67730\" aria-labelledby=\"instance-67730-header\" data-dockable=\"1\"><div class=\"header\"><div class=\"title\"><div class=\"block_action\"></div><h2 id=\"instance-67730-header\">Reloj</h2></div></div><div class=\"content\"><table class=\"clockTable\">\n" +
            "<tbody><tr class=\"r0\">\n" +
            "<td class=\"cell c0\" style=\"\"><img alt=\"Servidor\" class=\"smallicon\" title=\"Servidor\" src=\"https://campusvirtual.univalle.edu.co/moodle/theme/image.php/leatherbound/theme/1414765834/favicon\" /></td>\n" +
            "<td class=\"cell c1\" style=\"\">Servidor:</td>\n" +
            "<td class=\"cell c2 lastcol\" style=\"\"><input class=\"clock\" id=\"block_progress_serverTime\" value=\"Cargando...\" /></td>\n" +
            "</tr>\n" +
            "<tr class=\"r1 lastrow\">\n" +
            "<td class=\"cell c0\" style=\"\"><img src=\"https://campusvirtual.univalle.edu.co/moodle/pluginfile.php/238909/user/icon/leatherbound/f2?rev=1\" alt=\"Jimmy Page\" title=\"Jimmy Page\" class=\"userpicture\" width=\"16\" height=\"16\" /></td>\n" +
            "<td class=\"cell c1\" style=\"\">Usted:</td>\n" +
            "<td class=\"cell c2 lastcol\" style=\"\"><input class=\"clock\" id=\"block_progress_youTime\" value=\"Cargando...\" /></td>\n" +
            "</tr>\n" +
            "</tbody>\n" +
            "</table>\n" +
            "<noscript>Para permitir que los relojes se actualicen, habilite JavaScript en su navegador.</noscript></div></div><span id=\"sb-6\" class=\"skip-block-to\"></span><a href=\"#sb-7\" class=\"skip-block\">Saltar Mensajes</a><div id=\"inst447\" class=\"block_messages  block\" role=\"complementary\" data-block=\"messages\" data-instanceid=\"447\" aria-labelledby=\"instance-447-header\" data-dockable=\"1\"><div class=\"header\"><div class=\"title\"><div class=\"block_action\"></div><h2 id=\"instance-447-header\">Mensajes</h2></div></div><div class=\"content\"><div class=\"info\">No hay mensajes en espera</div><div class=\"footer\"><a href=\"https://campusvirtual.univalle.edu.co/moodle/message/index.php\">Mensajes</a></div></div></div><span id=\"sb-7\" class=\"skip-block-to\"></span><a href=\"#sb-8\" class=\"skip-block\">Saltar Cursos Públicos</a><div id=\"inst36692\" class=\"block_html  block\" role=\"complementary\" data-block=\"html\" data-instanceid=\"36692\" aria-labelledby=\"instance-36692-header\" data-dockable=\"1\"><div class=\"header\"><div class=\"title\"><div class=\"block_action\"></div><h2 id=\"instance-36692-header\">Cursos Públicos</h2></div></div><div class=\"content\"><div class=\"no-overflow\"><div id=\"cursos_publicos\">\n" +
            "<ul>\n" +
            "<li class=\"libro_rojo\"><a title=\"Agua y Cambio Climático en el Valle del Cauca\" href=\"https://campusvirtual.univalle.edu.co/moodle/course/view.php?id=13580\">Agua y Cambio Climático en el Valle del Cauca</a></li>\n" +
            "<li class=\"libro_rojo\"><a title=\"Apoyo Emocional: Hábitos de estudio\" href=\"https://campusvirtual.univalle.edu.co/moodle/course/view.php?id=2997\">Apoyo Emocional</a></li>\n" +
            "<li class=\"libro_rojo\"><a href=\"https://campusvirtual.univalle.edu.co/moodle/course/category.php?id=5\" title=\"Cursos dirigidos a los profesores\">Categoría Demo</a></li>\n" +
            "<li class=\"libro_rojo\"><a title=\"Cartelera Virtual - Oficina de Atención al Estudiante-OAE\" href=\"https://campusvirtual.univalle.edu.co/moodle/course/view.php?id=422\">Cartelera Virtual - OAE</a></li>\n" +
            "<li class=\"libro_rojo\"><a href=\"https://campusvirtual.univalle.edu.co/moodle/course/view.php?id=5670\" title=\"UNESCO: Comprensión y producción de textos\">Comprensión y Producción de Textos - UNESCO</a></li>\n" +
            "<li class=\"libro_rojo\"><a title=\"Diplomado Derechos de la Infancia\" href=\"https://campusvirtual.univalle.edu.co/moodle/course/view.php?id=14111\">Diplomado Derechos de la Infancia</a></li>\n" +
            "<li class=\"libro_rojo\"><a title=\"Encuentro de semilleros en la Sede Cartago\" href=\"https://campusvirtual.univalle.edu.co/moodle/course/view.php?id=19017\">III Encuentro Departamental RREDSI Valle</a></li>\n" +
            "<li class=\"libro_rojo\"><a title=\"Un espacio de estudio y formación de tutores de pregrado y postgrado para hacer acompañamiento e investigar sobre la problemática de la lectura y la escritura en las distintas disciplinas.\" href=\"https://campusvirtual.univalle.edu.co/moodle/course/view.php?id=17410\">Grupo de Apoyo para la Lectura y Escritura</a></li>\n" +
            "<li class=\"libro_rojo\"><a title=\"Inducción a Bibilotecas Univalle\" href=\"https://campusvirtual.univalle.edu.co/moodle/course/view.php?id=13854\">Inducción a la División de Bibliotecas</a></li>\n" +
            "<li class=\"libro_rojo\"><a href=\"https://campusvirtual.univalle.edu.co/moodle/course/view.php?id=9464\" title=\"Literatura Química\">Literatura Química</a></li>\n" +
            "<li class=\"libro_rojo\"><a href=\"https://campusvirtual.univalle.edu.co/moodle/course/view.php?id=733\" title=\"MAF buscar capacitar a los estudiantes en el manejo del tiempo, estilo y ritmo en los métodos de aprendizaje\">Metodologías de Autoformación - MAF</a></li>\n" +
            "</ul>\n" +
            "</div></div></div></div><span id=\"sb-8\" class=\"skip-block-to\"></span><a href=\"#sb-9\" class=\"skip-block\">Saltar Cursos DEMO</a><div id=\"inst173343\" class=\"block_html  block\" role=\"complementary\" data-block=\"html\" data-instanceid=\"173343\" aria-labelledby=\"instance-173343-header\" data-dockable=\"1\"><div class=\"header\"><div class=\"title\"><div class=\"block_action\"></div><h2 id=\"instance-173343-header\">Cursos DEMO</h2></div></div><div class=\"content\"><div class=\"no-overflow\"><p>Bienvenidos al espacio para realizar las <strong>practicas sobre la Plataforma Moodle del Campus Virtual Univalle</strong>.</p>\n" +
            "<p>Perfil de Profesor: <a target=\"_blank\" title=\"Curso Demo Profesores\" href=\"https://campusvirtual.univalle.edu.co/moodle/course/view.php?id=19137\">Curso Demo Profesores</a></p>\n" +
            "<p>Nombre de usuario y contraseña: <span style=\"color: #ff0000;\"><em><strong>profesor</strong></em></span></p></div></div></div><span id=\"sb-9\" class=\"skip-block-to\"></span>                    </div>\n" +
            "                </div>\n" +
            "                \n" +
            "            </div>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</div>\n" +
            "\n" +
            "<!-- END OF CONTENT -->\n" +
            "<div id=\"back-to-top\"> \n" +
            "        <a class=\"arrow\" href=\"#\">?</a> \n" +
            "        <a class=\"text\" href=\"#\">Back to Top</a> \n" +
            "    </div>\n" +
            "<!-- START OF FOOTER -->\n" +
            "\n" +
            "    <div id=\"page-footer\" class=\"wrapper\">\n" +
            "<!--    <p class=\"helplink\">\n" +
            "                </p>\n" +
            "-->\n" +
            "        <div class=\"sitelink\"><a title=\"Moodle\" href=\"http://moodle.org/\"><img style=\"width:100px;height:30px\" src=\"https://campusvirtual.univalle.edu.co/moodle/theme/image.php/leatherbound/core/1414765834/moodlelogo\" alt=\"moodlelogo\" /></a></div>    </div>\n" +
            "\n" +
            "<!-- END OF FOOTER -->\n" +
            "\n" +
            "</div>\n" +
            "\n" +
            "<div id=\"footer\"><div id=\"contactos\" class=\"block\">	\n" +
            "<div id=\"titulo_contacto\" class=\"header\">Mayor informacion</div>					\n" +
            "	<ul>\n" +
            "	  <li>Dirección de Nuevas Tecnologías y Educación Virtual - DINTEV</li>\n" +
            "	  <li>Vicerrectoría Académica </li>\n" +
            "	  <li>c.e: <a href=\"mailto:campusvirtual@correounivalle.edu.co\">campusvirtual@correounivalle.edu.co</a></li>\n" +
            "	  <li>Tel: +57 318 2649 ó Ext. Univalle: 2649 Oficina 2004 </li>	\n" +
            "	  <li>Edificio 317 - CREE, Ciudadela Universitaria Meléndez</li>\n" +
            "	  <li>Universidad del Valle - Cali, Colombia &copy; 2005-2013</li>\n" +
            "	</ul>					\n" +
            "</div>\n" +
            "<script type=\"text/javascript\">\n" +
            "//<![CDATA[\n" +
            "M.str = {\"moodle\":{\"lastmodified\":\"\\u00daltima modificaci\\u00f3n\",\"name\":\"Nombre\",\"error\":\"Error\",\"info\":\"Informaci\\u00f3n\",\"ok\":\"OK\",\"morehelp\":\"M\\u00e1s ayuda\",\"loadinghelp\":\"Cargando...\",\"cancel\":\"Cancelar\",\"yes\":\"S\\u00ed\"},\"repository\":{\"type\":\"Tipo\",\"size\":\"Tama\\u00f1o\",\"invalidjson\":\"Cadena JSON no v\\u00e1lida\",\"nofilesattached\":\"No se han adjuntado archivos\",\"filepicker\":\"Selector de archivos\",\"logout\":\"Salir\",\"nofilesavailable\":\"No hay archivos disponibles\",\"norepositoriesavailable\":\"Lo sentimos, ninguno de sus repositorios actuales puede devolver archivos en el formato solicitado.\",\"fileexistsdialogheader\":\"El archivo existe\",\"fileexistsdialog_editor\":\"Un archivo con el mismo nombre ya se ha adjuntado al texto que est\\u00e1 editando.\",\"fileexistsdialog_filemanager\":\"Un archivo con ese nombre ya ha sido adjuntado\",\"renameto\":\"Cambiar el nombre a\",\"referencesexist\":\"Existen {$a} archivos de alias\\/atajos que emplean este archivo como su or\\u00edgen\",\"select\":\"Seleccionar\"},\"block\":{\"addtodock\":\"Minimizar en la barra lateral\",\"undockitem\":\"Desacoplar este \\u00edtem\",\"dockblock\":\"Acoplar bloque {$a}\",\"undockblock\":\"Desacoplar bloque {$a}\",\"undockall\":\"Desacoplar todo\",\"hidedockpanel\":\"Esconder el panel desacoplado\",\"hidepanel\":\"Esconder panel\"},\"langconfig\":{\"thisdirectionvertical\":\"btt\"},\"block_simple_clock\":{\"clock_separator\":\":\",\"before_noon\":\"am\",\"after_noon\":\"pm\",\"day_names\":\"Dom,Lun,Mar,Mi\\u00e9,Jue,Vie,S\\u00e1b\"},\"admin\":{\"confirmation\":\"Confirmaci\\u00f3n\"}};\n" +
            "//]]>\n" +
            "</script>\n" +
            "<script type=\"text/javascript\">\n" +
            "//<![CDATA[\n" +
            "YUI().use('node', function(Y) {\n" +
            "M.util.load_flowplayer();\n" +
            "setTimeout(\"fix_column_widths()\", 20);\n" +
            "M.yui.galleryversion=\"2010.04.08-12-35\";Y.use(\"moodle-core-dock-loader\",function() {M.core.dock.loader.initLoader();\n" +
            "});\n" +
            "function legacy_activity_onclick_handler_1(e) { e.halt(); window.open('https://campusvirtual.univalle.edu.co/moodle/mod/resource/view.php?id=92&redirect=1', '', 'width=620,height=450,toolbar=no,location=no,menubar=no,copyhistory=no,status=no,directories=no,scrollbars=yes,resizable=yes'); return false; };\n" +
            "function legacy_activity_onclick_handler_2(e) { e.halt(); window.open('https://campusvirtual.univalle.edu.co/moodle/mod/page/view.php?id=156958&inpopup=1', '', 'width=1000,height=900,toolbar=no,location=no,menubar=no,copyhistory=no,status=no,directories=no,scrollbars=yes,resizable=yes'); return false; };\n" +
            "function legacy_activity_onclick_handler_3(e) { e.halt(); window.open('https://campusvirtual.univalle.edu.co/moodle/mod/page/view.php?id=157014&inpopup=1', '', 'width=1000,height=900,toolbar=no,location=no,menubar=no,copyhistory=no,status=no,directories=no,scrollbars=yes,resizable=yes'); return false; };\n" +
            "function legacy_activity_onclick_handler_4(e) { e.halt(); window.open('https://campusvirtual.univalle.edu.co/moodle/mod/resource/view.php?id=2005&redirect=1', '', 'width=620,height=450,toolbar=no,location=no,menubar=no,copyhistory=no,status=no,directories=no,scrollbars=yes,resizable=yes'); return false; };\n" +
            "function legacy_activity_onclick_handler_5(e) { e.halt(); window.open('https://campusvirtual.univalle.edu.co/moodle/mod/url/view.php?id=118902&redirect=1', '', 'width=1250,height=700,toolbar=no,location=no,menubar=no,copyhistory=no,status=no,directories=no,scrollbars=yes,resizable=yes'); return false; };\n" +
            "function legacy_activity_onclick_handler_6(e) { e.halt(); window.open('https://campusvirtual.univalle.edu.co/moodle/mod/resource/view.php?id=94&redirect=1', '', 'width=620,height=450,toolbar=no,location=no,menubar=no,copyhistory=no,status=no,directories=no,scrollbars=yes,resizable=yes'); return false; };\n" +
            "function legacy_activity_onclick_handler_7(e) { e.halt(); window.open('https://campusvirtual.univalle.edu.co/moodle/mod/resource/view.php?id=95&redirect=1', '', 'width=620,height=450,toolbar=no,location=no,menubar=no,copyhistory=no,status=no,directories=no,scrollbars=yes,resizable=yes'); return false; };\n" +
            "function legacy_activity_onclick_handler_8(e) { e.halt(); window.open('https://campusvirtual.univalle.edu.co/moodle/mod/resource/view.php?id=96&redirect=1', '', 'width=620,height=450,toolbar=no,location=no,menubar=no,copyhistory=no,status=no,directories=no,scrollbars=yes,resizable=yes'); return false; };\n" +
            "function legacy_activity_onclick_handler_9(e) { e.halt(); window.open('https://campusvirtual.univalle.edu.co/moodle/mod/resource/view.php?id=321049&redirect=1', '', 'width=800,height=700,toolbar=no,location=no,menubar=no,copyhistory=no,status=no,directories=no,scrollbars=yes,resizable=yes'); return false; };\n" +
            "function legacy_activity_onclick_handler_10(e) { e.halt(); window.open('https://campusvirtual.univalle.edu.co/moodle/mod/page/view.php?id=2995&inpopup=1', '', 'width=620,height=450,toolbar=no,location=no,menubar=no,copyhistory=no,status=no,directories=no,scrollbars=yes,resizable=yes'); return false; };\n" +
            "function legacy_activity_onclick_handler_11(e) { e.halt(); window.open('https://campusvirtual.univalle.edu.co/moodle/mod/page/view.php?id=3046&inpopup=1', '', 'width=620,height=450,toolbar=no,location=no,menubar=no,copyhistory=no,status=no,directories=no,scrollbars=yes,resizable=yes'); return false; };\n" +
            "function legacy_activity_onclick_handler_12(e) { e.halt(); window.open('https://campusvirtual.univalle.edu.co/moodle/mod/page/view.php?id=157004&inpopup=1', '', 'width=620,height=450,toolbar=no,location=no,menubar=no,copyhistory=no,status=no,directories=no,scrollbars=yes,resizable=yes'); return false; };\n" +
            "function legacy_activity_onclick_handler_13(e) { e.halt(); window.open('https://campusvirtual.univalle.edu.co/moodle/mod/page/view.php?id=407125&inpopup=1', '', 'width=620,height=450,toolbar=no,location=no,menubar=no,copyhistory=no,status=no,directories=no,scrollbars=yes,resizable=yes'); return false; };\n" +
            "M.yui.galleryversion=\"2010.04.08-12-35\";Y.use(\"moodle-block_navigation-navigation\",function() {M.block_navigation.init_add_tree({\"id\":\"142756\",\"instance\":\"142756\",\"candock\":true});\n" +
            "});\n" +
            "M.yui.galleryversion=\"2010.04.08-12-35\";Y.use(\"moodle-filter_glossary-autolinker\",function() {M.filter_glossary.init_filter_autolinking({\"courseid\":0});\n" +
            "});\n" +
            " M.util.js_pending('random54c40838e880414'); Y.use('block_simple_clock', function(Y) { M.block_simple_clock.initSimpleClock(Y, true, true, false, true, false, 2015, 0, 24, 16, 1, 47);  M.util.js_complete('random54c40838e880414'); });\n" +
            "M.util.help_popups.setup(Y);\n" +
            "M.yui.galleryversion=\"2010.04.08-12-35\";Y.use(\"moodle-core-popuphelp\",function() {M.core.init_popuphelp();\n" +
            "});\n" +
            "M.yui.galleryversion=\"2010.04.08-12-35\";Y.use(\"moodle-core-formautosubmit\",function() {M.core.init_formautosubmit({\"selectid\":\"single_select54c40838e880417\",\"nothing\":false});\n" +
            "});\n" +
            " M.util.js_pending('random54c40838e880418'); Y.on('domready', function() { M.util.js_complete(\"init\");  M.util.js_complete('random54c40838e880418'); });\n" +
            "\n" +
            "});\n" +
            "//]]>\n" +
            "</script>\n" +
            "</body>\n" +
            "</html>\n";
}
