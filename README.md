Gutenberg Indexer
=================

Overview
--------

This is a piece of code which, piece-by-piece pulls down books from Project 
Gutenberg to index them. This index is then used for search and item-item
similarity.

Status
------

Extremely alpha, most of the code is not there.

Tech
----

We will (or do) make use of, and give thanks for:

  * XStream XML parsing
  * Gutenberg's own catalog
  * Apache Lucene for fast, full-text searching
  * JUnit

Building
--------

```
mvn clean install
```

Why?
----

I want to have an awesome search, and moreso, I want kick-ass content-based
recommendations for books on Project Gutenberg.

