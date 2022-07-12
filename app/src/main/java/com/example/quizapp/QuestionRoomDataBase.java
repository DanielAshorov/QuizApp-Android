package com.example.quizapp;

import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import android.util.Log;

@Database(entities = {Questions.class},version = 8)
abstract class QuestionRoomDatabase extends RoomDatabase {

    private static QuestionRoomDatabase INSTANCE;
    public abstract QuestionDao questionDao();

    public static synchronized QuestionRoomDatabase getInstance(final Context context) {

        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    QuestionRoomDatabase.class, "questions_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(RoomDBCallback)
                    .build();

            // or query a dummy select statement
            INSTANCE.query("select 1", null);
        }

        return INSTANCE;

    }

    private static RoomDatabase.Callback RoomDBCallback = new RoomDatabase.Callback() {


        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

        private QuestionDao questionDao;


        private PopulateDbAsyncTask(QuestionRoomDatabase db) {

            questionDao = db.questionDao();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            Log.d("TRIVIA", "doInBackground: start insert");
            questionDao.insert(new Questions("Arsenal have won the only championship without a league loss", "True", "False", "Germany", "France", 3,"Sport", "en"));
            questionDao.insert(new Questions("How many Grand Slam titles Rafael Nadal have?","20", "25", "18","15", 1,"Sport", "en"));
            questionDao.insert(new Questions("The Olympics are held every how many years?","5", "2", "4","8", 3,"Sport", "en"));
            questionDao.insert(new Questions("Tiger Woods is a","Swimmer", "Basketball coach", "Tennis player","Golf player", 4,"Sport", "en"));
            questionDao.insert(new Questions("The last teem Michael Jordan played for was","LA Lakers", "Washington Wizards", "Chicago bulls","NY Knicks",  2,"Sport", "en"));
            questionDao.insert(new Questions("Cristiano Ronaldo plays for","Portugal", "Spain", "Argentina","Chile", 1,"Sport", "en"));
            questionDao.insert(new Questions("The NFL is the top league for","Basketball", "Soccer", "Hockey","Football", 4,"Sport", "en"));
            questionDao.insert(new Questions("Which of the following won the Champions League","Neymar", "Paul Pogba", "Antoine Griezmann","Eden Hazard ", 1,"Sport", "en"));
            questionDao.insert(new Questions("Which of the following clubs is not from London","Tottenham", "Chelsea", "Everton","Arsenal", 3,"Sport", "en"));
            questionDao.insert(new Questions("Who is the top scorer in the history of the EuroLeague","Juan Carlos Navarro", "Spanoulis", "Anthony Parker","Diamantidis", 2,"Sport", "en"));

            questionDao.insert(new Questions("מי זכה במונדיאל ב-2014?", "ארגנטינה", "ברזיל", "גרמניה", "צרפת", 3,"ספורט", "he"));
            questionDao.insert(new Questions("בכמה תארי גרנד סלאם זכה רפאל נדאל","20", "25", "18","15", 1,"ספורט", "he"));
            questionDao.insert(new Questions("בכל כמה שנים מתקיימת האולימפיאדה?","5", "2", "4","8", 3,"ספורט", "he"));
            questionDao.insert(new Questions("טייגר וודס הוא","שחיין	", "מאמן כדורסל", "שחקן טניס","שחקן גולף", 4,"ספורט", "he"));
            questionDao.insert(new Questions("מי היא הקבוצה האחרונה בה שיחק מייקל ג'ורדן","ל.א לייקרס", "וושינגטון ויזארדס", "שיקגו בולס","ניו יורק ניקס",  2,"ספורט", "he"));
            questionDao.insert(new Questions("כריסטיאנו רונאלדו משחק עבור","פורטוגל", "ספרד", "ארגנטינה","צ'ילה", 1,"ספורט", "he"));
            questionDao.insert(new Questions("באיזה תחום משוחקת ליגת ה-NFL","כדורסל", "fsurdk", "הוקי","כדורגל", 4,"ספורט", "he"));
            questionDao.insert(new Questions("מי מהבאים זכה בליגת האלופות","ניימאר", "פול פוגבה", "אנטואן גריזמן","עדן הזארד ", 1,"ספורט", "he"));
            questionDao.insert(new Questions("מי מהמועדנים הבאים אינו מלונדון","טוטנהאם", "צ'לסי", "אברטון","ארסנל", 3,"ספורט", "he"));
            questionDao.insert(new Questions("מי הוא מלך הסלים בתולדות היורוליג","חואן קרלוס נבארו", "וסיליס ספנוליס", "אנטוני פארקר","דיאמנטידיס", 2,"ספורט", "he"));



            questionDao.insert(new Questions("In which year did Justin Bieber release Baby","2012", "2009", "2006","2007", 2,"Music", "en"));
            questionDao.insert(new Questions("Who sang in James Bond movie (Skyfall)","Adele", "Rihanna", "Tina Turner","Madonna", 1,"Music", "en"));
            questionDao.insert(new Questions("What is the real name of Elton john","Paul David Hewson", "Robyn Fenty", "Stevland Hardaway Judkins","Reginald Dwight", 4,"Music", "en"));
            questionDao.insert(new Questions("Robert Plant was the lead singer of which rock band","Green Day", "The Rembrandts", "Led Zeppelin","Guro", 3,"Music", "en"));
            questionDao.insert(new Questions("Who was the youngest beatle","Paul McCarthney", "Ringo Starr", "George Harrison","John Lennon", 3,"Music", "en"));
            questionDao.insert(new Questions("Celine Dion won the Eurovision for","France", "Swiss", "Spain","Israel", 2,"Music", "en"));


            questionDao.insert(new Questions("באיזו שנה יצאו לאור שירו של ג'סטין ביבר בייבי?","2012", "2009", "2006","2007", 2,"מוזיקה", "he"));
            questionDao.insert(new Questions("מי שר את שיר הפתיחה בסרט סקייפול של ג'יימס בונד","אדל", "ריהאנה", "טינה טרנר","מדונה", 1,"מוזיקה", "he"));
            questionDao.insert(new Questions("מה הוא שמו האמיתי של אלטון ג'ון","פ'ול דיוויד", "רובין פנטי", "סטיב הארדווי ג'נקינס","רדינאלד דוויט", 4,"מוזיקה", "he"));
            questionDao.insert(new Questions("באיזו להקת רוק כיכב דיוויד פלאנט","גרין דיי", "הבלתי מאושרים", "לד זפלין","גורו", 3,"מוזיקה", "he"));
            questionDao.insert(new Questions("מי היה הזמר הצעיר ביותר בביטלס","פ'ול מקרטני", "רינגו סטאר", "ג'ורג' האריסון","ג'ון לנון", 3,"מוזיקה", "he"));
            questionDao.insert(new Questions("את מי ייצגה סלין דיון באירויוזיון","צרפת", "שוויץ", "ספרד","ישראל", 2,"מוזיקה", "he"));


            questionDao.insert(new Questions("What planet is closest to the sun","Mercury", "Neptune", "Jupiter","Venus", 1,"Science", "en"));
            questionDao.insert(new Questions("What is the largest planet","Mercury", "Neptune", "Jupiter","Venus", 3,"Science", "en"));
            questionDao.insert(new Questions("How many continents are there in the world","7", "4", "5","9", 1,"Science", "en"));

            questionDao.insert(new Questions("מי הוא כוכב הלכת הקרוב ביותר לשמש","מרקורי", "נפטון", "יופיטר","ונוס", 1,"מדע", "he"));
            questionDao.insert(new Questions("מה הוא כוכב הלכת הגדול ביותר","מרקורי", "נפטון", "יופיטר","ונוס", 3,"מדע", "he"));
            questionDao.insert(new Questions("כמה יבשות יש בעולם","7", "4", "5","9", 1,"Science", "מדע"));


            questionDao.insert(new Questions("Who is 007","Superman", "James Bond", "Ben Afflek","Richard Harrison", 2,"Movies", "en"));
            questionDao.insert(new Questions("Who is part of the Avengers","Batman", "Doom", "Wolverine","Hulk", 4,"Movies", "en"));
            questionDao.insert(new Questions("Who directed \"Kill Bill\"","Tarantino", "Spielberg", "Scorsese Martin","Christopher Nolan", 1,"Movies", "en"));
            questionDao.insert(new Questions("Who directed \"Inception\"","Tarantino", "Spielberg", "Scorsese Martin","Christopher Nolan", 4,"Movies", "en"));
            questionDao.insert(new Questions("Who played \"The Gladiator\"","Gerard Butler", "Tom Cruz", "Russell Crowe","Brad Pitt", 2,"Movies", "en"));
            questionDao.insert(new Questions("25.\tWho played \"The Mask\"","Jim Carrey", "Sasha Baron Cohen", "Adam Sandler","Brad Pitt", 1,"Movies", "en"));


            questionDao.insert(new Questions("מי הוא 007","סופרמן", "ג'יימס בונד", "בן אפלק","ריצ'רד באריסון", 2,"סרטים", "he"));
            questionDao.insert(new Questions("מי הוא חלק מהנוקמים","באטמן", "דום", "וולברין","הענק הירוק", 4,"סרטים", "he"));
            questionDao.insert(new Questions("מי ביים את קיל ביל","טרנטינו", "שפילברג", "מרטין סקורסזה","כריסטופר נולאן", 1,"סרטים", "he"));
            questionDao.insert(new Questions("מי ביים את התחלה","טרנטינו", "שפילברג", "מרטין סקורסזה","כריסטופר נולאן", 4,"סרטים", "he"));
            questionDao.insert(new Questions("מי שיחק את הגלדיאטור","ג'רארד באטלר", "טום קרוז", "ראסל קרואו","בראד פיט", 2,"סרטים", "he"));
            questionDao.insert(new Questions("מי שיחק את המסכה","ג'ים קארי", "סשה ברון כהן", "אדם סנדלר","בראד פיט", 1,"סרטים", "he"));


            questionDao.insert(new Questions("Which popular company designed the first CPU","Apple", "Dell", "Intel","IBM", 3,"Technology", "en"));
            questionDao.insert(new Questions("Which of the following is not a web browser","Chrome", "Google-Drive", "Safari","Edge", 2,"Technology", "en"));
            questionDao.insert(new Questions("What company developed a computer with the codename \"Lisa\"","Apple", "Microsoft", "Intel","IBM", 1,"Technology", "en"));
            questionDao.insert(new Questions("What does \"IT\" stand for","Integrated Technology", "Individual Technology", "Information Technology","Implied Technology", 3,"Technology", "en"));
            questionDao.insert(new Questions("Which of the following is not a software language","PHP", "C++", "C#","Docker", 4,"Technology", "en"));
            questionDao.insert(new Questions(" When was the internet created","1968", "1972", "1989","1992", 1,"Technology", "en"));
            questionDao.insert(new Questions("How many bits make a byte","16 bits", "8 bits", "24 bits","12 bits", 2,"Technology", "en"));
            questionDao.insert(new Questions("What is the meaning of CPU","Central Processing Unit", "Critical Processing Unit", "Crucial Processing Unit","Central Printing Unit", 1,"Technology", "en"));


            questionDao.insert(new Questions("מי עיצבה את המעבד הראשון","אפל", "דל", "אינטל","IBM", 3,"טכנולוגיה", "en"));
            questionDao.insert(new Questions("מה מהבאים אינו דפדפן אינטרנט","כרום", "גוגל דרייב", "ספארי","אדג'", 2,"טכנולוגיה", "he"));
            questionDao.insert(new Questions("איזו חברה השיקה מחשב בשם ליסה","אפל", "מייקרוסופט", "אינטל","IBM", 1,"טכנולוגיה", "he"));
            questionDao.insert(new Questions("מה ראשי התיבות של IT","Integrated Technology", "Individual Technology", "Information Technology","Implied Technology", 3,"טכנולוגיה", "he"));
            questionDao.insert(new Questions("מה מהבאים אינו שפת תכנות","PHP", "C++", "C#","Docker", 4,"טכנולוגיה", "he"));
            questionDao.insert(new Questions("מתי הומצא האינטרנט","1968", "1972", "1989","1992", 1,"טכנולוגיה", "he"));
            questionDao.insert(new Questions("כמה ביטים יש בבייט אחד","16 ביטים", "8 ביטים", "24 ביטים","12 ביטים", 2,"טכנולוגיה", "he"));
            questionDao.insert(new Questions("מה ראשי התיבות של CPU","Central Processing Unit", "Critical Processing Unit", "Crucial Processing Unit","Central Printing Unit", 1,"טכנולוגיה", "he"));
            Log.d("TRIVIA", "doInBackground: end insert");

            return null;
        }
    }
}



