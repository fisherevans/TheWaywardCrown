use feature qw(say);
use DBI;
use File::Copy;

# See - http://mailliststock.wordpress.com/2007/03/01/sqlite-examples-with-bash-perl-and-python/

my $db;
my $skel = "db_skel.twc";
my $file = "testsave.twc";
my $qs = 0;

copy($skel, $file);
say "Creating new DB file: $file, from: $skel";
say "Connecting to DB...";
$db = DBI->connect("dbi:SQLite:$file", "", "", {RaiseError => 1, AutoCommit => 1});
say "Please wait, running queries...\n";

newCharacter("Kira Yamato", "player", 1, 0, 100, 0, 0, "res/sprites/player_sprite.png", "res/sprites/player_profile.png", "res/sprites/player_icon.png", 40, 40, 7, 5, 7, 3, 5);
newCharacter("Straif", "testmap_straif", "", "", "", "", "", "res/sprites/testmap_straif_sprite.png", "res/sprites/testmap_straif_profile.png", "res/sprites/testmap_straif_icon.png");
	newText("testmap_straif_controler", "null");
	newInt("testmap_straif_x", 120);
	newInt("testmap_straif_y", 38);
newCharacter("Esere", "testmap_esere", "", "", "", "", "", "res/sprites/testmap_esere_sprite.png", "res/sprites/testmap_esere_profile.png", "res/sprites/testmap_esere_icon.png");

say "\n\n" . $qs . " queries have completed.\nClosing the DB connection...";
# CLOSING DB
$db->disconnect();
system("pause");

sub query
{
	#say "QUERY > $_[0]";
	$db->do($_[0]);
	print ".";
	$qs++;
}

sub newCharacter
{
	query('INSERT INTO characters ("name", "reference", "level", "xp_current", "xp_target", "combat_type", "gender", "image_sprite", "image_profile", "image_icon", "stat_hp", "stat_hp_current", "stat_nrg", "stat_dex", "stat_mag", "stat_str", "stat_con") VALUES  ("'.$_[0].'","'.$_[1].'","'.$_[2].'","'.$_[3].'","'.$_[4].'","'.$_[5].'","'.$_[6].'","'.$_[7].'","'.$_[8].'","'.$_[9].'","'.$_[10].'","'.$_[11].'","'.$_[12].'","'.$_[13].'","'.$_[14].'","'.$_[15].'","'.$_[16].'");');
}

sub newText
{
	query('INSERT INTO attributes_text ("reference", "value") VALUES ("'.$_[0].'","'.$_[1].'");');
}

sub newInt
{
	query('INSERT INTO attributes_int ("reference", "value") VALUES ("'.$_[0].'","'.$_[1].'");');
}