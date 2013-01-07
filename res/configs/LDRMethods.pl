use feature qw(say);

$ENTITIES = 0;
$MAP = 1;
$LIGHTS = 2;
$TRIGGERS = 3;

say ":MAP";
$current = $MAP;

sub setMap
{
    if($current != $MAP)
    { say ":MAP"; $current = $MAP; }
}

sub setEntities
{
    if($current != $ENTITIES)
    { say ":ENTITIES"; $current = $ENTITIES;  }
}

sub setLights
{
    if($current != $LIGHTS)
    { say ":LIGHTS"; $current = $LIGHTS; }
}

sub setTriggers
{
    if($current != $TRIGGERS)
    { say ":TRIGGERS"; $current = $TRIGGERS; }
}

# night(r, g, b);
sub night
{
	setLights();
	say "    NIGHT $_[0] $_[1] $_[2]";
}

# dawn(r, g, b);
sub dawn
{
	setLights();
	say "    DAWN $_[0] $_[1] $_[2]";
}

# day(r, g, b);
sub day
{
	setLights();
	say "    DAY $_[0] $_[1] $_[2]";
}

# dusk(r, g, b);
sub dusk
{
	setLights();
	say "    DUSK $_[0] $_[1] $_[2]";
}

sub defaultDaycolors
{
	night(50, 55, 105);
	dawn(153, 178, 180);
	day(251, 255, 237);
	dusk(207, 159, 116);
}

# mapSettings(tmxFile, cameraEnt, playerEnt);
sub mapSettings
{
    setMap();
    say "  ALL res/maps/tmx/$_[0] res/maps/tilesets $_[1] $_[2]";
}

sub light
{
    setLights();
    say "  NEW $_[0] $_[1] $_[2] $_[3] $_[4]";
}

# animatedEntity(name, displayName, actions,
#				 sprite, icon,
#				 x, y, face);
sub animatedEntity
{
    setEntities();
	say "  NEW $_[0]";
	say "    CHARACTER animated $_[2] $_[1]";
	say "    IMAGES $_[3] $_[4]";
	say "    POSITION $_[5] $_[6] $_[7]";
}

# movableEntity(name, displayName, actions,
#				sprite, icon,
#				x, y, face,
#				controller, speed);
sub movableEntity
{
    setEntities();
	say "  NEW $_[0]";
	say "    CHARACTER movable $_[2] $_[1]";
	say "    IMAGES $_[3] $_[4]";
	say "    POSITION $_[5] $_[6] $_[7]";
	say "    MOVEMENT $_[8] $_[9]";
}

# trigger(x, y,
#		  times, action, entEffects);
sub trigger
{
    setTriggers();
    say "  NEW";
    say "    X $_[0]";
    say "    Y $_[1]";
    say "    TIMES $_[2]";
    say "    ACTIONS $_[3]";
    say "    EFFECTS $_[4]";
}

# triggerBig(x1, y1,
#			 x2, y2,
#			 times, action, entEffects);
sub triggerBig
{
    setTriggers();
    say "  NEW";
    say "    X1 ". $_[0];
    say "    Y1 ". $_[1];
    say "    X2 ". $_[2];
    say "    Y2 ". $_[3];
    say "    TIMES ". $_[4];
    say "    ACTIONS ". $_[5];
    say "    EFFECTS ". $_[6];
}

# Needs this to "require" this in another file.
1;