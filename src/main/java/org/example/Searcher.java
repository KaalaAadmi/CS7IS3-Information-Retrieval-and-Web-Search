package org.example;

import java.io.IOException;
import java.io.PrintWriter;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.document.Document;

//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Paths;
public class Searcher {

    private Searcher() {}

//    This function performs the search based upon the query passed from the controller function
    public static void performSearch(IndexSearcher searcher, PrintWriter writer, Integer queryNumber, Query query) throws IOException {
        TopDocs result = searcher.search(query, 1400); // n= the number of documents to be available in results.
        ScoreDoc hits[] = result.scoreDocs;
        int i=0;
        while(i<hits.length){
            Document doc = searcher.doc(hits[i].doc);
            /*
             * Write the results in the format expected by trec_eval:
             * | Query Number | 0 | Document ID | Rank | Score | "STANDARD" |
             * (https://stackoverflow.com/questions/4275825/how-to-evaluate-a-search-retrieval-engine-using-trec-eval)
             */
            writer.println(queryNumber + " 0 " + doc.get("ID") + " " + i + " " + hits[i].score + " STANDARD");
            i++;
        }
    }
}
