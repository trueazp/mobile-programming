package com.miaowmere.animelist;

import java.util.ArrayList;

public class AnimeDatasource {
    private static String[] animeTitles = {
            "Bleach",
            "Code Geass",
            "Death Note",
            "Dragon Ball",
            "Neon Genesis Evangelion",
            "Full Metal Alchemist",
            "Inuyasha",
            "Naruto Shippuden",
            "One Piece",
            "Attack On Titan The Final Season"
    };

    private static String[] animeDesc = {
            "Bleach (stylized as BLEACH) is a Japanese manga series written and illustrated by Tite Kubo. Bleach follows the adventures of the hotheaded teenager Ichigo Kurosaki, who inherits his parents' destiny after he obtains the powers of a Soul Reaper—a death personification similar to the Grim Reaper—from another Soul Reaper, Rukia Kuchiki. His new-found powers force him to take on the duties of defending humans from evil spirits and guiding departed souls to the afterlife, and set him on journeys to various ghostly realms of existence.",
            "Code Geass: Lelouch of the Rebellion (Japanese: コードギアス 反逆のルルーシュ, Hepburn: Kōdo Giasu: Hangyaku no Rurūshu), often referred to simply as Code Geass, is a Japanese anime series produced by Sunrise. It was directed by Gorō Taniguchi and written by Ichirō Ōkouchi, with original character designs by Clamp. Set in an alternate timeline, the series follows the exiled prince Lelouch vi Britannia, who obtains the \"power of absolute obedience\" from a mysterious woman named C.C. Using this supernatural power, known as Geass, he leads a rebellion against the rule of the Holy Britannian Empire, commanding a series of mecha battles.",
            "Death Note (stylized as DEATH NOTE) is a Japanese manga series written by Tsugumi Ohba and illustrated by Takeshi Obata. The story follows Light Yagami, a teen genius who stumbles across a mysterious otherworldly notebook: the \"Death Note\", which belonged to the Shinigami Ryuk and grants the user the supernatural ability to kill anyone whose name is written in its pages. The series centers around Light's subsequent attempts to use the Death Note to carry out a worldwide massacre of individuals whom he deems morally unworthy of life to change the world into a utopian society without crime, using the alias of a god-like vigilante named \"Kira\" and the subsequent efforts of an elite task-force of law enforcement officers, consisting of members of the Japanese police force, led by L, an enigmatic international detective whose past is shrouded in mystery, to apprehend him and end his reign of terror.",
            "Dragon Ball (Japanese: ドラゴンボール, Hepburn: Doragon Bōru) is a Japanese media franchise created by Akira Toriyama in 1984. The initial manga, written and illustrated by Toriyama, was serialized in Weekly Shōnen Jump from 1984 to 1995. Dragon Ball was initially inspired by the classical 16th century Chinese novel Journey to the West, as well as Hong Kong martial arts films. The series follows the adventures of the protagonist, Son Goku, from his childhood through adulthood as he trains in martial arts. He spends his childhood far from civilization until he meets a teen girl named Bulma, who encourages him to join her quest in exploring the world in search of the seven orbs known as the Dragon Balls, which summon a wish-granting dragon when gathered. Along his journey, Goku makes several other friends, becomes a family man, discovers his alien heritage, and battles a wide variety of villains.",
            "Neon Genesis Evangelion[4] (Japanese: 新世紀エヴァンゲリオン, Hepburn: Shinseiki Evangerion, lit. \"New Century Gospel\") is a Japanese mecha anime television series produced by Gainax and Tatsunoko Production, directed by Hideaki Anno and broadcast on TV Tokyo from October 1995 to March 1996. Evangelion is set fifteen years after a worldwide cataclysm, particularly in the futuristic fortified city of Tokyo-3. The protagonist is Shinji, a teenage boy who was recruited by his father Gendo to the shadowy organization Nerv to pilot a giant bio-machine mecha called an \"Evangelion\" into combat with beings called \"Angels\".",
            "Fullmetal Alchemist (Japanese: 鋼の錬金術師, Hepburn: Hagane no Renkinjutsushi, lit. \"The Steel Alchemist\") is a Japanese manga series written and illustrated by Hiromu Arakawa. It was serialized in Square Enix's Monthly Shōnen Gangan shōnen manga magazine between July 2001 and June 2010; the publisher later collected the individual chapters into twenty-seven tankōbon volumes. The steampunk world of Fullmetal Alchemist is primarily styled after the European Industrial Revolution. Set in a fictional universe in which alchemy is one of the advanced natural techniques revolved around scientific laws of equivalent exchange, the series follows the adventures of two alchemist brothers named Edward and Alphonse Elric, who are searching for the philosopher's stone to restore their bodies after a failed attempt to bring their mother back to life using alchemy.",
            "Inuyasha (犬夜叉, lit. \"Dog Yaksha\") is a Japanese manga series written and illustrated by Rumiko Takahashi. The series begins with Kagome Higurashi, a fifteen-year-old middle school girl from modern-day Tokyo who is transported to the Japanese era of Sengoku period after falling into a well in her family shrine, where she meets the half-dog demon, half-human Inuyasha. After the sacred Shikon Jewel re-emerges from deep inside Kagome's body, she accidentally shatters it into dozens of fragments that scatter across Japan. Inuyasha and Kagome set to recover the Jewel's fragments, and through their quest they are joined by the lecherous monk Miroku, the demon slayer Sango, and the fox demon Shippo. Together, they journey to restore the Shikon Jewel before it falls into the hands of the evil half-demon Naraku.",
            "Naruto: Shippūden (NARUTO -ナルト- 疾風伝, Naruto Shippūden, literally meaning: Naruto Hurricane Chronicles) is the anime adaptation of Part II of the manga, set two-and-a-half years after Naruto Uzumaki leaves Konohagakure to train with Jiraiya. Twelve years before the start of the series, the Nine-Tails attacked Konohagakure destroying much of the village and taking many lives. The leader of the village, the Fourth Hokage, sacrificed his life to seal the Nine-Tails into a newborn, Naruto Uzumaki. Orphaned by the attack, Naruto was shunned by the villagers, who out of fear and anger, viewed him as the Nine-Tails itself. Though the Third Hokage outlawed speaking about anything related to the Nine-Tails, the children — taking their cues from their parents — inherited the same animosity towards Naruto. In his thirst to be acknowledged, Naruto vowed he would one day become the greatest Hokage.",
            "One Piece is a Japanese manga series written and illustrated by Eiichiro Oda. It has been serialized in Shueisha's Weekly Shōnen Jump magazine since July 1997, with its individual chapters compiled into 97 tankōbon volumes as of September 2020. The story follows the adventures of Monkey D. Luffy, a boy whose body gained the properties of rubber after unintentionally eating a Devil Fruit. With his crew of pirates, named the Straw Hat Pirates, Luffy explores the Grand Line in search of the world's ultimate treasure known as \"One Piece\" in order to become the next King of the Pirates. In August 2020, it was announced that One Piece was approaching its final arc.",
            "Attack on Titan (Japanese: 進撃の巨人, Hepburn: Shingeki no Kyojin, lit. \"The Attack Titan\") is a Japanese manga series written and illustrated by Hajime Isayama. It is set in a world where humanity lives inside cities surrounded by enormous walls that protect them from gigantic man-eating humanoids referred to as Titans; the story follows Eren Yeager, who vows to exterminate the Titans after a Titan brings about the destruction of his hometown and the death of his mother. Attack on Titan has been serialized in Kodansha's monthly Bessatsu Shōnen Magazine since September 2009 and collected into 32 tankōbon volumes as of September 2020."
    };

    private static String[] animeGenre = {
            "Genre: Fighting-Shounen",
            "Genre: Action Drama",
            "Genre: Crime Fiction, Thriller",
            "Genre: Adventure",
            "Genre: Action Drama",
            "Genre: Adventure",
            "Genre: Fighting-Shounen",
            "Genre: Fighting-Shounen",
            "Genre: Fighting-Shounen",
            "Genre: Action Drama",
    };

    private static int[] animePosters = {
            R.drawable.bleach_poster,
            R.drawable.code_geass_poster,
            R.drawable.death_note_poster,
            R.drawable.dragon_ball_poster,
            R.drawable.evangelion_poster,
            R.drawable.fma_poster,
            R.drawable.inuyasha_poster,
            R.drawable.naruto_shippuden_poster,
            R.drawable.one_piece_poster,
            R.drawable.snk_s4_poster
    };

    static ArrayList<Anime> getListData() {
        ArrayList<Anime> list = new ArrayList<>();
        for (int pos = 0; pos < animeTitles.length; pos++) {
            Anime anime = new Anime();
            anime.setTitle(animeTitles[pos]);
            anime.setDescription(animeDesc[pos]);
            anime.setGenre(animeGenre[pos]);
            anime.setPoster(animePosters[pos]);
            list.add(anime);
        }
    return list;
    }
}
