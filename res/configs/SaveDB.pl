use feature qw(say);
use DBI;

# See - http://mailliststock.wordpress.com/2007/03/01/sqlite-examples-with-bash-perl-and-python/

my $db;

sub openDB
{
	say ">Connecting to DB file: ../../$_[0]";
	$db = DBI->connect("dbi:SQLite:../../$_[0]", "", "", {RaiseError => 1, AutoCommit => 1});
}

sub closeDB
{
	$db->disconnect();
}

sub getAttrib
{
	$exists = rowExists($_[0]);
}

sub rowExists
{
	$result = $db->selectall_arrayref('SELECT * FROM global_attribs WHERE "name"="' . $_[0] . '"');
	return $#result;
}

sub dbDO
{
	$db->do($_[0]);
}

sub dbSELECT
{
	my $result = $db->selectall_arrayref($_[0]);
	return $result;
}

1;

#!/usr/bin/perl -w
#use DBI;
##use strict;
#my $db = DBI->connect("dbi:SQLite:test.db", "", "",
#{RaiseError => 1, AutoCommit => 1});#

#$db->do("CREATE TABLE n (id INTEGER PRIMARY KEY, f TEXT, l TEXT)");
#$db->do("INSERT INTO n VALUES (NULL, 'john', 'smith')");
#my $all = $db->selectall_arrayref("SELECT * FROM n");

#foreach my $row (@$all) {
#my ($id, $first, $last) = @$row;
#print "$id|$first|$lastn";
#}