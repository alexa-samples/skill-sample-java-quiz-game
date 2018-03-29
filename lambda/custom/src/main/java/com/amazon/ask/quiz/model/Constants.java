package com.amazon.ask.quiz.model;

import java.util.Arrays;
import java.util.List;

public class Constants {

    public static final List<State> STATES = Arrays.asList(
            new State("Alabama", "AL","Montgomery", "1819", "22"),
            new State("Alaska", "AK","Juneau", "1959", "49"),
            new State("Arizona", "AZ","Phoenix", "1912", "48"),
            new State("Arkansas", "AR","Little Rock", "1836", "25"),
            new State("California", "CA","Sacramento", "1850", "31"),
            new State("Colorado", "CO","Denver", "1876", "38"),
            new State("Connecticut", "CT","Hartford", "1788", "5"),
            new State("Delaware", "DE","Dover", "1787", "1"),
            new State("Florida", "FL","Tallahassee", "1845", "27"),
            new State("Georgia", "GA","Atlanta", "1788", "4"),
            new State("Hawaii", "HI","Honolulu", "1959", "50"),
            new State("Idaho", "ID","Boise", "1890", "43"),
            new State("Illinois", "IL","Springfield", "1818", "21"),
            new State("Indiana", "IN","Indianapolis", "1816", "19"),
            new State("Iowa", "IA","Des Moines", "1846", "29"),
            new State("Kansas", "KS","Topeka", "1861", "34"),
            new State("Kentucky", "KY","Frankfort", "1792", "15"),
            new State("Louisiana", "LA","Baton Rouge", "1812", "18"),
            new State("Maine", "ME","Augusta", "1820", "23"),
            new State("Maryland", "MD","Annapolis", "1788", "7"),
            new State("Massachusetts", "MA","Boston", "1788", "6"),
            new State("Michigan", "MI","Lansing", "1837", "26"),
            new State("Minnesota", "MN","St. Paul", "1858", "32"),
            new State("Mississippi", "MS","Jackson", "1817", "20"),
            new State("Missouri", "MO","Jefferson City", "1821", "24"),
            new State("Montana", "MT","Helena", "1889", "41"),
            new State("Nebraska", "NE","Lincoln", "1867", "37"),
            new State("Nevada", "NV","Carson City", "1864", "36"),
            new State("New Hampshire", "NH","Concord", "1788", "9"),
            new State("New Jersey", "NJ","Trenton", "1787", "3"),
            new State("New Mexico", "NM","Santa Fe", "1912", "47"),
            new State("New York", "NY","Albany", "1788", "11"),
            new State("North Carolina", "NC","Raleigh", "1789", "12"),
            new State("North Dakota", "ND","Bismarck", "1889", "39"),
            new State("Ohio", "OH","Columbus", "1803", "17"),
            new State("Oklahoma", "OK","Oklahoma City", "1907", "46"),
            new State("Oregon", "OR","Salem", "1859", "33"),
            new State("Pennsylvania", "PA","Harrisburg", "1787", "2"),
            new State("Rhode Island", "RI","Providence", "1790", "13"),
            new State("South Carolina", "SC","Columbia", "1788", "8"),
            new State("South Dakota", "SD","Pierre", "1889", "40"),
            new State("Tennessee", "TN","Nashville", "1796", "16"),
            new State("Texas", "TX","Austin", "1845", "28"),
            new State("Utah", "UT","Salt Lake City", "1896", "45"),
            new State("Vermont", "VT","Montpelier", "1791", "14"),
            new State("Virginia", "VA","Richmond", "1788", "10"),
            new State("Washington", "WA","Olympia", "1889", "42"),
            new State("West Virginia", "WV","Charleston", "1863", "35"),
            new State("Wisconsin", "WI","Madison", "1848", "30"),
            new State("Wyoming", "WY","Cheyenne", "1890", "44")
    );

    public static boolean USE_CARDS_FLAG = true;

    public static String WELCOME_MESSAGE = "Welcome to the United States Quiz Game!  You can ask me about any of the fifty states and their capitals, or you can ask me to start a quiz.  What would you like to do?";

    public static String START_QUIZ_MESSAGE = "OK.  I will ask you 10 questions about the United States.";

    // This is the message a user will hear when they try to cancel or stop the
    // skill, or when they finish a quiz.
    public static String EXIT_SKILL_MESSAGE = "Thank you for playing the United States Quiz Game!  Let's play again soon!";

    // This is the message a user will hear after they ask (and hear) about a
    // specific data element.
    public static String REPROMPT_MESSAGE = "Which other state or capital would you like to know about?";

    // This is the message a user will hear when they ask Alexa for help in your
    // skill.
    public static String HELP_MESSAGE = "I know lots of things about the United States.  You can ask me about a state or a capital, and I'll tell you what I know.  You can also test your knowledge by asking me to start a quiz.  What would you like to do?";

    public static List<String> CORRECT_RESPONSES = Arrays.asList("Booya", "All righty", "Bam", "Bazinga", "Bingo", "Boom", "Bravo", "Cha Ching", "Cheers", "Dynomite",
            "Hip hip hooray", "Hurrah", "Hurray", "Huzzah", "Oh dear.  Just kidding.  Hurray", "Kaboom", "Kaching", "Oh snap", "Phew",
            "Righto", "Way to go", "Well done", "Whee", "Woo hoo", "Yay", "Wowza", "Yowsa");

    public static List<String> INCORRECT_RESPONSES = Arrays.asList("Argh", "Aw man", "Blarg", "Blast", "Boo", "Bummer", "Darn", "D'oh", "Dun dun dun", "Eek", "Honk", "Le sigh",
            "Mamma mia", "Oh boy", "Oh dear", "Oof", "Ouch", "Ruh roh", "Shucks", "Uh oh", "Wah wah", "Whoops a daisy", "Yikes");

}
