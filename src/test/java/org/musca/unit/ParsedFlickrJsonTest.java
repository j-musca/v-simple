package org.musca.unit;

import static org.junit.Assert.*;

import org.junit.Test;
import org.musca.representation.ParsedFlickrJson;

import java.io.IOException;

/**
 * Created by jonas on 13.09.14.
 */
public class ParsedFlickrJsonTest {
    
    private final String brokenFlickrJson = "jsonFlickrFeed({\n" +
            "\t\t\"title\": \"Recent Uploads tagged hahahah\",\n" +
            "\t\t\"link\": \"http://www.flickr.com/photos/tags/hahahah/\",\n" +
            "\t\t\"description\": \"\",\n" +
            "\t\t\"modified\": \"2014-06-24T14:20:08Z\",\n" +
            "\t\t\"generator\": \"http://www.flickr.com/\",\n" +
            "\t\t\"items\": [\n" +
            "\t   {\n" +
            "\t\t\t\"title\": \"Me? Belgrade\",\n" +
            "\t\t\t\"link\": \"http://www.flickr.com/photos/123702037@N04/14517563493/\",\n" +
            "\t\t\t\"media\": {\"m\":\"http://farm6.staticflickr.com/5078/14517563493_ea50abb610_m.jpg\"},\n" +
            "\t\t\t\"date_taken\": \"2014-06-24T07:20:00-08:00\",\n" +
            "\t\t\t\"description\": \" <p><a href=\\\"http://www.flickr.com/people/123702037@N04/\\\">Nikola Slavković<\\/a> posted a photo:<\\/p> <p><a href=\\\"http://www.flickr.com/photos/123702037@N04/14517563493/\\\" title=\\\"Me? Belgrade\\\"><img src=\\\"http://farm6.staticflickr.com/5078/14517563493_ea50abb610_m.jpg\\\" width=\\\"240\\\" height=\\\"71\\\" alt=\\\"Me? Belgrade\\\" /><\\/a><\\/p> \",\n" +
            "\t\t\t\"published\": \"2014-06-24T14:20:08Z\",\n" +
            "\t\t\t\"author\": \"nobody@flickr.com (Nikola Slavković)\",\n" +
            "\t\t\t\"author_id\": \"123702037@N04\",\n" +
            "\t\t\t\"tags\": \"me fountain look canon lens photography eos 50mm pretty cut no awesome center full pro hd mm belgrade distance instead fontana far hahaha bg mua hahahah objective cutted 600d i\"\n" +
            "\t   },\n" +
            "\t   {\n" +
            "\t\t\t\"title\": \"Uma barca pra começar os trabalhos! hahahah\",\n" +
            "\t\t\t\"link\": \"http://www.flickr.com/photos/jpcamolez/14182825770/\",\n" +
            "\t\t\t\"media\": {\"m\":\"http://farm6.staticflickr.com/5536/14182825770_17c0ab6754_m.jpg\"},\n" +
            "\t\t\t\"date_taken\": \"2014-06-07T19:35:15-08:00\",\n" +
            "\t\t\t\"description\": \" <p><a href=\\\"http://www.flickr.com/people/jpcamolez/\\\">jpcamolez<\\/a> posted a photo:<\\/p> <p><a href=\\\"http://www.flickr.com/photos/jpcamolez/14182825770/\\\" title=\\\"Uma barca pra começar os trabalhos! hahahah\\\"><img src=\\\"http://farm6.staticflickr.com/5536/14182825770_17c0ab6754_m.jpg\\\" width=\\\"240\\\" height=\\\"240\\\" alt=\\\"Uma barca pra começar os trabalhos! hahahah\\\" /><\\/a><\\/p> \",\n" +
            "\t\t\t\"published\": \"2014-06-07T22:35:15Z\",\n" +
            "\t\t\t\"author\": \"nobody@flickr.com (jpcamolez)\",\n" +
            "\t\t\t\"author_id\": \"57860222@N08\",\n" +
            "\t\t\t\"tags\": \"barca uma pra os trabalhos hahahah começar\"\n" +
            "\t   },\n" +
            "\t   {\n" +
            "\t\t\t\"title\": \"road\",\n" +
            "\t\t\t\"link\": \"http://www.flickr.com/photos/frogmanor/13815374234/\",\n" +
            "\t\t\t\"media\": {\"m\":\"http://farm4.staticflickr.com/3807/13815374234_46a505fcb1_m.jpg\"},\n" +
            "\t\t\t\"date_taken\": \"2014-04-11T21:30:34-08:00\",\n" +
            "\t\t\t\"description\": \" <p><a href=\\\"http://www.flickr.com/people/frogmanor/\\\">sunny-drunk<\\/a> posted a photo:<\\/p> <p><a href=\\\"http://www.flickr.com/photos/frogmanor/13815374234/\\\" title=\\\"road\\\"><img src=\\\"http://farm4.staticflickr.com/3807/13815374234_46a505fcb1_m.jpg\\\" width=\\\"240\\\" height=\\\"130\\\" alt=\\\"road\\\" /><\\/a><\\/p> \",\n" +
            "\t\t\t\"published\": \"2014-04-13T18:47:55Z\",\n" +
            "\t\t\t\"author\": \"nobody@flickr.com (sunny-drunk)\",\n" +
            "\t\t\t\"author_id\": \"67087571@N00\",\n" +
            "\t\t\t\"tags\": \"road travel spain alone empty tag horizon sunny roadtrip tags minimal line tenerife ontheroad hahahah brokenline sunnydrunk\"\n" +
            "\t   },\n" +
            "\t   {\n" +
            "\t\t\t\"title\": \"tumblr kitten\",\n" +
            "\t\t\t\"link\": \"http://www.flickr.com/photos/54932793@N04/10930264415/\",\n" +
            "\t\t\t\"media\": {\"m\":\"http://farm6.staticflickr.com/5495/10930264415_b3df4ff7a6_m.jpg\"},\n" +
            "\t\t\t\"date_taken\": \"2013-11-17T12:03:26-08:00\",\n" +
            "\t\t\t\"description\": \" <p><a href=\\\"http://www.flickr.com/people/54932793@N04/\\\">Taylor Whitehurst<\\/a> posted a photo:<\\/p> <p><a href=\\\"http://www.flickr.com/photos/54932793@N04/10930264415/\\\" title=\\\"tumblr kitten\\\"><img src=\\\"http://farm6.staticflickr.com/5495/10930264415_b3df4ff7a6_m.jpg\\\" width=\\\"240\\\" height=\\\"160\\\" alt=\\\"tumblr kitten\\\" /><\\/a><\\/p> \",\n" +
            "\t\t\t\"published\": \"2013-11-18T18:56:32Z\",\n" +
            "\t\t\t\"author\": \"nobody@flickr.com (Taylor Whitehurst)\",\n" +
            "\t\t\t\"author_id\": \"54932793@N04\",\n" +
            "\t\t\t\"tags\": \"light shadow film look cat kitten shadows curtain famous meow tortie hahahah tumblr vision:mountain=0535 vision:clouds=0659 vision:outdoor=0888 vision:sky=09\"\n" +
            "\t   },\n" +
            "\t   {\n" +
            "\t\t\t\"title\": \"zuadona\",\n" +
            "\t\t\t\"link\": \"http://www.flickr.com/photos/rrsopast/9887158414/\",\n" +
            "\t\t\t\"media\": {\"m\":\"http://farm3.staticflickr.com/2872/9887158414_a49845fdb2_m.jpg\"},\n" +
            "\t\t\t\"date_taken\": \"2013-07-03T21:57:02-08:00\",\n" +
            "\t\t\t\"description\": \" <p><a href=\\\"http://www.flickr.com/people/rrsopast/\\\">m. defacio<\\/a> posted a photo:<\\/p> <p><a href=\\\"http://www.flickr.com/photos/rrsopast/9887158414/\\\" title=\\\"zuadona\\\"><img src=\\\"http://farm3.staticflickr.com/2872/9887158414_a49845fdb2_m.jpg\\\" width=\\\"240\\\" height=\\\"180\\\" alt=\\\"zuadona\\\" /><\\/a><\\/p> \",\n" +
            "\t\t\t\"published\": \"2013-09-23T01:18:52Z\",\n" +
            "\t\t\t\"author\": \"nobody@flickr.com (m. defacio)\",\n" +
            "\t\t\t\"author_id\": \"75971936@N07\",\n" +
            "\t\t\t\"tags\": \"friends love girl fe hahahah\"\n" +
            "\t   },\n" +
            "\t   {\n" +
            "\t\t\t\"title\": \"Aww.\",\n" +
            "\t\t\t\"link\": \"http://www.flickr.com/photos/21803532@N08/9457831235/\",\n" +
            "\t\t\t\"media\": {\"m\":\"http://farm6.staticflickr.com/5503/9457831235_11eab70ff7_m.jpg\"},\n" +
            "\t\t\t\"date_taken\": \"2013-08-07T12:25:15-08:00\",\n" +
            "\t\t\t\"description\": \" <p><a href=\\\"http://www.flickr.com/people/21803532@N08/\\\">Brekka<\\/a> posted a photo:<\\/p> <p><a href=\\\"http://www.flickr.com/photos/21803532@N08/9457831235/\\\" title=\\\"Aww.\\\"><img src=\\\"http://farm6.staticflickr.com/5503/9457831235_11eab70ff7_m.jpg\\\" width=\\\"240\\\" height=\\\"180\\\" alt=\\\"Aww.\\\" /><\\/a><\\/p> <p>This may come as a surprise but ................I\\'m not the biggest cat person but this was cute! This is at my dad\\'s house<\\/p>\",\n" +
            "\t\t\t\"published\": \"2013-08-07T17:29:19Z\",\n" +
            "\t\t\t\"author\": \"nobody@flickr.com (Brekka)\",\n" +
            "\t\t\t\"author_id\": \"21803532@N08\",\n" +
            "\t\t\t\"tags\": \"b love by cat t xt tv er child y you d 5 tag w pussy ct kiddy x we v ty f r e u porn loves re xxx rv yu triple rc sdf xr aaa vt tcr tr yi fw ybi hahahah uy tru byte xdd tey xe tuy ehehe pedophile fwe lololol vev hohohohohoho ytj utyu yeeees kidporn yeeeeeeees uploaded:by=flickrmobile flickriosapp:filter=iguana sexuallobster huwvhtuheuhxie wrxe sdby ejhchuihutru ijyiv yrjuiovj hihwxuihueruyvhuhtr qvhuihutvhuehtuweuhtv pornofkids yrhtuihrvurhyvyhi\"\n" +
            "\t   },\n" +
            "\t   {\n" +
            "\t\t\t\"title\": \"Kijk mijn Aura...\",\n" +
            "\t\t\t\"link\": \"http://www.flickr.com/photos/61410648@N07/9175122932/\",\n" +
            "\t\t\t\"media\": {\"m\":\"http://farm4.staticflickr.com/3733/9175122932_f6966d033d_m.jpg\"},\n" +
            "\t\t\t\"date_taken\": \"2013-06-29T23:43:28-08:00\",\n" +
            "\t\t\t\"description\": \" <p><a href=\\\"http://www.flickr.com/people/61410648@N07/\\\">doppio d\\'Oro<\\/a> posted a photo:<\\/p> <p><a href=\\\"http://www.flickr.com/photos/61410648@N07/9175122932/\\\" title=\\\"Kijk mijn Aura...\\\"><img src=\\\"http://farm4.staticflickr.com/3733/9175122932_f6966d033d_m.jpg\\\" width=\\\"240\\\" height=\\\"160\\\" alt=\\\"Kijk mijn Aura...\\\" /><\\/a><\\/p> \",\n" +
            "\t\t\t\"published\": \"2013-06-30T13:21:25Z\",\n" +
            "\t\t\t\"author\": \"nobody@flickr.com (doppio d\\'Oro)\",\n" +
            "\t\t\t\"author_id\": \"61410648@N07\",\n" +
            "\t\t\t\"tags\": \"er op kus hahahah kusje dichtbij popje bennietzowildhoor3vino waseigenlijkeensulligfeestjemaarlijktheelwatopfotos watgeenlach watpraatjijveel behlvjij gaathetwelgoedmetje oikweetalwatjijnietlolligvindthahaha niemanddiedatgoogled\"\n" +
            "\t   },\n" +
            "\t   {\n" +
            "\t\t\t\"title\": \"Liberec\",\n" +
            "\t\t\t\"link\": \"http://www.flickr.com/photos/tomashlava/9062239130/\",\n" +
            "\t\t\t\"media\": {\"m\":\"http://farm4.staticflickr.com/3813/9062239130_8c897e57c5_m.jpg\"},\n" +
            "\t\t\t\"date_taken\": \"2013-06-16T20:25:11-08:00\",\n" +
            "\t\t\t\"description\": \" <p><a href=\\\"http://www.flickr.com/people/tomashlava/\\\">abolito<\\/a> posted a photo:<\\/p> <p><a href=\\\"http://www.flickr.com/photos/tomashlava/9062239130/\\\" title=\\\"Liberec\\\"><img src=\\\"http://farm4.staticflickr.com/3813/9062239130_8c897e57c5_m.jpg\\\" width=\\\"240\\\" height=\\\"160\\\" alt=\\\"Liberec\\\" /><\\/a><\\/p> \",\n" +
            "\t\t\t\"published\": \"2013-06-16T21:33:53Z\",\n" +
            "\t\t\t\"author\": \"nobody@flickr.com (abolito)\",\n" +
            "\t\t\t\"author_id\": \"57467551@N03\",\n" +
            "\t\t\t\"tags\": \"trip boy red people fish eye canon photography eos high no air bored free boring fisheye shit ddd limit fell hahahah 550d\"\n" +
            "\t   },\n" +
            "\t   {\n" +
            "\t\t\t\"title\": \"\",\n" +
            "\t\t\t\"link\": \"http://www.flickr.com/photos/tomashlava/9060019989/\",\n" +
            "\t\t\t\"media\": {\"m\":\"http://farm4.staticflickr.com/3781/9060019989_0a28926e5b_m.jpg\"},\n" +
            "\t\t\t\"date_taken\": \"2013-06-16T20:28:04-08:00\",\n" +
            "\t\t\t\"description\": \" <p><a href=\\\"http://www.flickr.com/people/tomashlava/\\\">abolito<\\/a> posted a photo:<\\/p> <p><a href=\\\"http://www.flickr.com/photos/tomashlava/9060019989/\\\" title=\\\"\\\"><img src=\\\"http://farm4.staticflickr.com/3781/9060019989_0a28926e5b_m.jpg\\\" width=\\\"240\\\" height=\\\"160\\\" alt=\\\"\\\" /><\\/a><\\/p> \",\n" +
            "\t\t\t\"published\": \"2013-06-16T21:33:51Z\",\n" +
            "\t\t\t\"author\": \"nobody@flickr.com (abolito)\",\n" +
            "\t\t\t\"author_id\": \"57467551@N03\",\n" +
            "\t\t\t\"tags\": \"trip boy red people fish eye canon photography eos high no air bored free boring fisheye shit ddd limit fell hahahah 550d\"\n" +
            "\t   },\n" +
            "\t   {\n" +
            "\t\t\t\"title\": \"\",\n" +
            "\t\t\t\"link\": \"http://www.flickr.com/photos/tomashlava/9062236156/\",\n" +
            "\t\t\t\"media\": {\"m\":\"http://farm6.staticflickr.com/5500/9062236156_c45c284478_m.jpg\"},\n" +
            "\t\t\t\"date_taken\": \"2013-06-16T20:27:41-08:00\",\n" +
            "\t\t\t\"description\": \" <p><a href=\\\"http://www.flickr.com/people/tomashlava/\\\">abolito<\\/a> posted a photo:<\\/p> <p><a href=\\\"http://www.flickr.com/photos/tomashlava/9062236156/\\\" title=\\\"\\\"><img src=\\\"http://farm6.staticflickr.com/5500/9062236156_c45c284478_m.jpg\\\" width=\\\"240\\\" height=\\\"160\\\" alt=\\\"\\\" /><\\/a><\\/p> \",\n" +
            "\t\t\t\"published\": \"2013-06-16T21:33:54Z\",\n" +
            "\t\t\t\"author\": \"nobody@flickr.com (abolito)\",\n" +
            "\t\t\t\"author_id\": \"57467551@N03\",\n" +
            "\t\t\t\"tags\": \"trip air free fell no limit shit hahahah ddd bored boring high red canon eos 550d photography people boy fish eye fisheye\"\n" +
            "\t   },\n" +
            "\t   {\n" +
            "\t\t\t\"title\": \"Mmmm.... lekker!\",\n" +
            "\t\t\t\"link\": \"http://www.flickr.com/photos/61410648@N07/8794945961/\",\n" +
            "\t\t\t\"media\": {\"m\":\"http://farm6.staticflickr.com/5325/8794945961_9c23803fcc_m.jpg\"},\n" +
            "\t\t\t\"date_taken\": \"2013-05-23T18:45:48-08:00\",\n" +
            "\t\t\t\"description\": \" <p><a href=\\\"http://www.flickr.com/people/61410648@N07/\\\">doppio d\\'Oro<\\/a> posted a photo:<\\/p> <p><a href=\\\"http://www.flickr.com/photos/61410648@N07/8794945961/\\\" title=\\\"Mmmm.... lekker!\\\"><img src=\\\"http://farm6.staticflickr.com/5325/8794945961_9c23803fcc_m.jpg\\\" width=\\\"240\\\" height=\\\"193\\\" alt=\\\"Mmmm.... lekker!\\\" /><\\/a><\\/p> \",\n" +
            "\t\t\t\"published\": \"2013-05-23T18:58:08Z\",\n" +
            "\t\t\t\"author\": \"nobody@flickr.com (doppio d\\'Oro)\",\n" +
            "\t\t\t\"author_id\": \"61410648@N07\",\n" +
            "\t\t\t\"tags\": \"hahahah anditmakesmecry watdoeikdattochweergoed daarverveelikmevastniet maarnietgetreurdmijnechtevriendjeszijnookbestlief eneenlekkerehamburgertoe hebgesolliciteerdindelft fullmoonfriday erisgeentijdvoorons okébeetjegrootspraak eentjebijnaklaaranderergpril maardiesiszolekkervoormnego doejijeensnietzoonaardig benjegekgewordenofzo achhetisandersdanjedenktbezopenenverzopenopdeboot vandaageendiepgaandkatergesprekoverliefdeenloslaten\"\n" +
            "\t   },\n" +
            "\t   {\n" +
            "\t\t\t\"title\": \"laughter\",\n" +
            "\t\t\t\"link\": \"http://www.flickr.com/photos/cap_tivatingphotography/8687243717/\",\n" +
            "\t\t\t\"media\": {\"m\":\"http://farm8.staticflickr.com/7053/8687243717_33dce270d4_m.jpg\"},\n" +
            "\t\t\t\"date_taken\": \"2013-04-27T22:44:00-08:00\",\n" +
            "\t\t\t\"description\": \" <p><a href=\\\"http://www.flickr.com/people/cap_tivatingphotography/\\\">rapture∞<\\/a> posted a photo:<\\/p> <p><a href=\\\"http://www.flickr.com/photos/cap_tivatingphotography/8687243717/\\\" title=\\\"laughter\\\"><img src=\\\"http://farm8.staticflickr.com/7053/8687243717_33dce270d4_m.jpg\\\" width=\\\"240\\\" height=\\\"161\\\" alt=\\\"laughter\\\" /><\\/a><\\/p> <p>we all use it<\\/p>\",\n" +
            "\t\t\t\"published\": \"2013-04-28T05:47:04Z\",\n" +
            "\t\t\t\"author\": \"nobody@flickr.com (rapture∞)\",\n" +
            "\t\t\t\"author_id\": \"44189735@N04\",\n" +
            "\t\t\t\"tags\": \"portrait ass me girl hair long pretty bad picture tags wish really enough okay hahahah selfie\"\n" +
            "\t   },\n" +
            "\t   {\n" +
            "\t\t\t\"title\": \"البحر لا ينام. و في يقظة البحر نعزية لروح لا تنام\",\n" +
            "\t\t\t\"link\": \"http://www.flickr.com/photos/isabella07/8492294740/\",\n" +
            "\t\t\t\"media\": {\"m\":\"http://farm9.staticflickr.com/8228/8492294740_594d945f13_m.jpg\"},\n" +
            "\t\t\t\"date_taken\": \"2013-02-20T19:16:34-08:00\",\n" +
            "\t\t\t\"description\": \" <p><a href=\\\"http://www.flickr.com/people/isabella07/\\\">isabelrita<\\/a> posted a photo:<\\/p> <p><a href=\\\"http://www.flickr.com/photos/isabella07/8492294740/\\\" title=\\\"البحر لا ينام. و في يقظة البحر نعزية لروح لا تنام\\\"><img src=\\\"http://farm9.staticflickr.com/8228/8492294740_594d945f13_m.jpg\\\" width=\\\"160\\\" height=\\\"240\\\" alt=\\\"البحر لا ينام. و في يقظة البحر نعزية لروح لا تنام\\\" /><\\/a><\\/p> <p>&quot;The sea never sleeps, and in the sleeplessness of the sea is a consolation to a soul that never sleeps.&quot;<br /> <br /> --<br /> Anachem is depressing.<\\/p>\",\n" +
            "\t\t\t\"published\": \"2013-02-20T12:46:12Z\",\n" +
            "\t\t\t\"author\": \"nobody@flickr.com (isabelrita)\",\n" +
            "\t\t\t\"author_id\": \"56566590@N03\",\n" +
            "\t\t\t\"tags\": \"girl darkness sp lampshade hahahah teenagephotographer nikond3100 isabelrita\"\n" +
            "\t   },\n" +
            "\t   {\n" +
            "\t\t\t\"title\": \"230.365 happy vajayjyaday\",\n" +
            "\t\t\t\"link\": \"http://www.flickr.com/photos/ferdiography/8476090114/\",\n" +
            "\t\t\t\"media\": {\"m\":\"http://farm9.staticflickr.com/8108/8476090114_ecfc59999a_m.jpg\"},\n" +
            "\t\t\t\"date_taken\": \"2013-02-15T03:01:06-08:00\",\n" +
            "\t\t\t\"description\": \" <p><a href=\\\"http://www.flickr.com/people/ferdiography/\\\">Ferdi Galeon<\\/a> posted a photo:<\\/p> <p><a href=\\\"http://www.flickr.com/photos/ferdiography/8476090114/\\\" title=\\\"230.365 happy vajayjyaday\\\"><img src=\\\"http://farm9.staticflickr.com/8108/8476090114_ecfc59999a_m.jpg\\\" width=\\\"240\\\" height=\\\"159\\\" alt=\\\"230.365 happy vajayjyaday\\\" /><\\/a><\\/p> <p>i think i read that on twitter or instagram, an it stuck. another day another dollar can\\'t believe how fast this year is going by i feel like im just blinking an each day passes by ah wells ramblings. Be my valentine?<br /> _____________<br /> Sb600 shot into soft box @ 1/80 power behind camera. <br /> Sb600 shot behind me at 1/50 power with a gel to give it that red glow. <br /> <br /> <a href=\\\"https://www.facebook.com/FerdinandGaleonPhotography\\\" rel=\\\"nofollow\\\">Facebook<\\/a> | <a href=\\\"http://ferdigaleonphotos.tumblr.com/\\\" rel=\\\"nofollow\\\">Tumblr<\\/a> | <a href=\\\"http://ferdinandg.blogspot.com/\\\" rel=\\\"nofollow\\\">Blog<\\/a> | <a href=\\\"http://galeonphotography.com/\\\" rel=\\\"nofollow\\\">Website<\\/a><\\/p>\",\n" +
            "\t\t\t\"published\": \"2013-02-15T11:35:51Z\",\n" +
            "\t\t\t\"author\": \"nobody@flickr.com (Ferdi Galeon)\",\n" +
            "\t\t\t\"author_id\": \"8440951@N06\",\n" +
            "\t\t\t\"tags\": \"red get umbrella project reading weird nikon day you sb600 hard lion tags trying just valentines filipino these 365 hip hop attention gels softbox hahahah oye sb900 d7000\"\n" +
            "\t   },\n" +
            "\t   {\n" +
            "\t\t\t\"title\": \"Oh, hai!\",\n" +
            "\t\t\t\"link\": \"http://www.flickr.com/photos/heathersflickr/8374199135/\",\n" +
            "\t\t\t\"media\": {\"m\":\"http://farm9.staticflickr.com/8510/8374199135_098e75771f_m.jpg\"},\n" +
            "\t\t\t\"date_taken\": \"2012-12-27T17:15:23-08:00\",\n" +
            "\t\t\t\"description\": \" <p><a href=\\\"http://www.flickr.com/people/heathersflickr/\\\">♥heather♥<\\/a> posted a photo:<\\/p> <p><a href=\\\"http://www.flickr.com/photos/heathersflickr/8374199135/\\\" title=\\\"Oh, hai!\\\"><img src=\\\"http://farm9.staticflickr.com/8510/8374199135_098e75771f_m.jpg\\\" width=\\\"160\\\" height=\\\"240\\\" alt=\\\"Oh, hai!\\\" /><\\/a><\\/p> <p>Me and my fuzzy wuzzy bear and a fisheye. :D<\\/p>\",\n" +
            "\t\t\t\"published\": \"2013-01-13T02:02:02Z\",\n" +
            "\t\t\t\"author\": \"nobody@flickr.com (♥heather♥)\",\n" +
            "\t\t\t\"author_id\": \"14267765@N02\",\n" +
            "\t\t\t\"tags\": \"bear winter red silly girl self canon hair lens funny fuzzy head fisheye scruffy hahahah honeybunch 60d sigma10mmf28fisheye lotsoffreakinghair rosywinterycheeksandnoses\"\n" +
            "\t   },\n" +
            "\t   {\n" +
            "\t\t\t\"title\": \"Lookie what I found under the tree.\",\n" +
            "\t\t\t\"link\": \"http://www.flickr.com/photos/heathersflickr/8282509915/\",\n" +
            "\t\t\t\"media\": {\"m\":\"http://farm9.staticflickr.com/8504/8282509915_772fbe760d_m.jpg\"},\n" +
            "\t\t\t\"date_taken\": \"2012-12-17T23:45:44-08:00\",\n" +
            "\t\t\t\"description\": \" <p><a href=\\\"http://www.flickr.com/people/heathersflickr/\\\">♥heather♥<\\/a> posted a photo:<\\/p> <p><a href=\\\"http://www.flickr.com/photos/heathersflickr/8282509915/\\\" title=\\\"Lookie what I found under the tree.\\\"><img src=\\\"http://farm9.staticflickr.com/8504/8282509915_772fbe760d_m.jpg\\\" width=\\\"160\\\" height=\\\"240\\\" alt=\\\"Lookie what I found under the tree.\\\" /><\\/a><\\/p> <p>Also, also...fisheye! :)<\\/p>\",\n" +
            "\t\t\t\"published\": \"2012-12-18T05:15:21Z\",\n" +
            "\t\t\t\"author\": \"nobody@flickr.com (♥heather♥)\",\n" +
            "\t\t\t\"author_id\": \"14267765@N02\",\n" +
            "\t\t\t\"tags\": \"christmas blue tree lens bokeh box fisheye nails bow present tiffanyco tiffanys heheh hahahah canon60d christmastreebokeh sigma10mmf28 mythumblooksweird foraudreybychinaglaze everyonekeptsayingdontbuyafisheye butiaminlovewithit iguessmyhoneygotthehintfromthenails\"\n" +
            "\t   },\n" +
            "\t   {\n" +
            "\t\t\t\"title\": \"\",\n" +
            "\t\t\t\"link\": \"http://www.flickr.com/photos/taysaholocheski/8264744603/\",\n" +
            "\t\t\t\"media\": {\"m\":\"http://farm9.staticflickr.com/8339/8264744603_7f27f29395_m.jpg\"},\n" +
            "\t\t\t\"date_taken\": \"2012-12-11T15:26:01-08:00\",\n" +
            "\t\t\t\"description\": \" <p><a href=\\\"http://www.flickr.com/people/taysaholocheski/\\\">taysaholocheski<\\/a> posted a photo:<\\/p> <p><a href=\\\"http://www.flickr.com/photos/taysaholocheski/8264744603/\\\" title=\\\"\\\"><img src=\\\"http://farm9.staticflickr.com/8339/8264744603_7f27f29395_m.jpg\\\" width=\\\"240\\\" height=\\\"161\\\" alt=\\\"\\\" /><\\/a><\\/p> \",\n" +
            "\t\t\t\"published\": \"2012-12-11T23:27:05Z\",\n" +
            "\t\t\t\"author\": \"nobody@flickr.com (taysaholocheski)\",\n" +
            "\t\t\t\"author_id\": \"90350306@N02\",\n" +
            "\t\t\t\"tags\": \"family baby white 3 black cute girl smile eyes mine sister no supermodel wb rafa cutest rafaela nenem hahahah maninha\"\n" +
            "\t   },\n" +
            "\t   {\n" +
            "\t\t\t\"title\": \"\",\n" +
            "\t\t\t\"link\": \"http://www.flickr.com/photos/rrsopast/8136477273/\",\n" +
            "\t\t\t\"media\": {\"m\":\"http://farm9.staticflickr.com/8467/8136477273_635b3bab3b_m.jpg\"},\n" +
            "\t\t\t\"date_taken\": \"2010-02-24T14:34:05-08:00\",\n" +
            "\t\t\t\"description\": \" <p><a href=\\\"http://www.flickr.com/people/rrsopast/\\\">m. defacio<\\/a> posted a photo:<\\/p> <p><a href=\\\"http://www.flickr.com/photos/rrsopast/8136477273/\\\" title=\\\"\\\"><img src=\\\"http://farm9.staticflickr.com/8467/8136477273_635b3bab3b_m.jpg\\\" width=\\\"240\\\" height=\\\"181\\\" alt=\\\"\\\" /><\\/a><\\/p> <p>dedinho de anã<\\/p>\",\n" +
            "\t\t\t\"published\": \"2012-10-29T22:13:32Z\",\n" +
            "\t\t\t\"author\": \"nobody@flickr.com (m. defacio)\",\n" +
            "\t\t\t\"author_id\": \"75971936@N07\",\n" +
            "\t\t\t\"tags\": \"fuckoff hahahah\"\n" +
            "\t   },\n" +
            "\t   {\n" +
            "\t\t\t\"title\": \"Want to see a magic trick?\",\n" +
            "\t\t\t\"link\": \"http://www.flickr.com/photos/85357538@N05/8060574066/\",\n" +
            "\t\t\t\"media\": {\"m\":\"http://farm9.staticflickr.com/8457/8060574066_b3de37c1f8_m.jpg\"},\n" +
            "\t\t\t\"date_taken\": \"2012-10-06T21:54:24-08:00\",\n" +
            "\t\t\t\"description\": \" <p><a href=\\\"http://www.flickr.com/people/85357538@N05/\\\">LucyandtheWolves<\\/a> posted a photo:<\\/p> <p><a href=\\\"http://www.flickr.com/photos/85357538@N05/8060574066/\\\" title=\\\"Want to see a magic trick?\\\"><img src=\\\"http://farm9.staticflickr.com/8457/8060574066_b3de37c1f8_m.jpg\\\" width=\\\"180\\\" height=\\\"240\\\" alt=\\\"Want to see a magic trick?\\\" /><\\/a><\\/p> \",\n" +
            "\t\t\t\"published\": \"2012-10-06T21:02:19Z\",\n" +
            "\t\t\t\"author\": \"nobody@flickr.com (LucyandtheWolves)\",\n" +
            "\t\t\t\"author_id\": \"85357538@N05\",\n" +
            "\t\t\t\"tags\": \"make up dark serious batman joker knight why villian hahahah codsplay\"\n" +
            "\t   },\n" +
            "\t   {\n" +
            "\t\t\t\"title\": \"12g stretch\",\n" +
            "\t\t\t\"link\": \"http://www.flickr.com/photos/tacohannah/8049178148/\",\n" +
            "\t\t\t\"media\": {\"m\":\"http://farm9.staticflickr.com/8311/8049178148_28d9e6a5cd_m.jpg\"},\n" +
            "\t\t\t\"date_taken\": \"2012-10-02T21:33:58-08:00\",\n" +
            "\t\t\t\"description\": \" <p><a href=\\\"http://www.flickr.com/people/tacohannah/\\\">tacohannah<\\/a> posted a photo:<\\/p> <p><a href=\\\"http://www.flickr.com/photos/tacohannah/8049178148/\\\" title=\\\"12g stretch\\\"><img src=\\\"http://farm9.staticflickr.com/8311/8049178148_28d9e6a5cd_m.jpg\\\" width=\\\"240\\\" height=\\\"180\\\" alt=\\\"12g stretch\\\" /><\\/a><\\/p> <p>i think i said 14mm on my last stretching photo.. sorry. -_- its g. not mm. <br /> <br /> follow me on tumblr!<br /> breathing-hope.tumblr.com<\\/p>\",\n" +
            "\t\t\t\"published\": \"2012-10-03T02:34:11Z\",\n" +
            "\t\t\t\"author\": \"nobody@flickr.com (tacohannah)\",\n" +
            "\t\t\t\"author_id\": \"73319401@N06\",\n" +
            "\t\t\t\"tags\": \"butts qt 12mm gauging stretching perf perfect ears luv love lava lav hahahah what ahh hannah y u funny world will never know butt for just keep typing words get your photos searched upon ok there you go going oging oops typos ahahha sorry not srry bye\"\n" +
            "\t   }\n" +
            "        ]\n" +
            "})";
    
    @Test
    public void testParserDoesNotThrowException() throws IOException {
        ParsedFlickrJson flickrJson = new ParsedFlickrJson(brokenFlickrJson);
        assertNotNull(flickrJson);
    }

    @Test
    public void testParserReturnsExpectedJson() throws IOException {
        ParsedFlickrJson flickrJson = new ParsedFlickrJson(brokenFlickrJson);
        assertEquals(6, flickrJson.getParsedContent().size());
    }
}
