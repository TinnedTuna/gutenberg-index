package org.turner.indexing;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.turner.ConfigDAO;
import org.turner.model.catalog.Book;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

/**
 * Created by turner on 20/12/13.
 */
public class BookIndexWriter implements Runnable {

  private static final Logger logger = LoggerFactory.getLogger(BookIndexWriter.class);

  private BlockingQueue<Book> bookQueue;
  private ConfigDAO configDAO;
  private IndexWriter indexWriter;

  public BookIndexWriter(BlockingQueue<Book> bookQueue) {
    this.bookQueue = bookQueue;
  }

  public void run() {
    try {
      initialiseIndexWriter();
      processQueue();
    } catch (IOException e) {
      logger.error("Cannot open index for writing, shutting down BookIndexer", e);
    } catch (InterruptedException e) {
      logger.error("BookIndexWriter shutting down.");
    }
  }

  private void processQueue() throws InterruptedException {
    Book inputBook;
    // bookQueue.take() is blocking until a book is placed on the queue.
    while ((inputBook = bookQueue.take()) != null) {
      indexBook(inputBook);
    }
  }

  private void indexBook(Book inputBook) {
    if (inputBook == null) {
      logger.error("Attempted to index a null book. Aborting.");
      return;
    }
    Document doc = new Document();
    doc.add(new LongField("catalogId", inputBook.getCatalogId(), Field.Store.YES));
    for (String author : inputBook.getAuthor()) {
      doc.add(new StringField("author", author, Field.Store.YES));
    }
    for (String title : inputBook.getTitle()) {
      doc.add(new StringField("title",title, Field.Store.YES));
    }
    doc.add(new TextField("contents", inputBook.getContents(), Field.Store.NO));
    try {
      indexWriter.addDocument(doc);
    } catch (IOException e) {
      logger.error("Could not index book, catalogId: {}", inputBook.getCatalogId());
    }
  }

  private void initialiseIndexWriter() throws IOException {
    FSDirectory fsDirectory = new SimpleFSDirectory(configDAO.getIndexDirectory());
    indexWriter = new IndexWriter(fsDirectory, new IndexWriterConfig(Version.LUCENE_46, new StandardAnalyzer(Version.LUCENE_46)));
  }

}
