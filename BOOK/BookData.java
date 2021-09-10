public class BookData {
    public static Book[] inventory = new Book[50];
    public static int size = 24;
    static {
    
        inventory[0] = new Book("9781416936473","The Hatchet","Gary Paulsen",true,
                "Young Adult Fiction");

        inventory[1] = new Book("9780440414803", "Holes", "Louis Sacha", false,
                "Adventure");

        inventory[2] = new Book("9781501175466", "IT","Stephen King", true,
                "Horror");
        
        inventory[3] = new Book("19782501175466", "The Shining", 
                "Stephen King", 
                true, "Horror"); 
        
        
        inventory[4] = new Book("19382501175466", "Pet Semetary", 
                "Stephen King", 
                true, "Horror"); 
         
        inventory[5] = new Book("18782501175466", "House of Leaves", 
                "Mark Z. D", 
                false, "Horror"); 
        
         inventory[6] = new Book("15482501175466", "Frankenstein", 
                 "Mary Shelly", 
                 true, "Horror"); 
         
         inventory[7] = new Book("12282501175466", "Dracula", "Bram Stoker", 
                 true,
                 "Horror"); 
         
         inventory[8] = new Book("19733501175466", "Haunted House", 
                 "Shirley Jackson", 
                 true, "Horror"); 
         
         inventory[9] = new Book("22782501175466", "Treasure Island", 
                 "Robert Louis", 
                 false, "Adventure"); 
         
         inventory[10] = new Book("44782501175466", "Into the Wild", 
                 "Jon Krakauea", 
                 true, "Adventure"); 
         
         inventory[11] = new Book("26782501175466", "Dune", "Frank Herben", 
                 false,
                 "Adventure"); 
         
         inventory[12] = new Book("12782501175466", "Inca Gold", "Clive Cussle", 
                 false,
                 "Adventure");
         
         inventory[13] = new Book("22552501175466", "Roughing It", "Mark Twain", 
                 true, "Adventure"); 
         
         inventory[14] = new Book("22782501175466", "Heart of Darkness", 
                 "Joseph Conrad", 
                 true, "Adventure"); 
         
         inventory[15] = new Book("72782501175466", "Sahara", "Clive Cussle", 
                 false,
                 "Adventure"); 
         
         inventory[16] = new Book("90782501175466", "The Sea-Wolf", 
                 "Jack London", 
                 true, "Adventure"); 
         
         inventory[17] = new Book("254782501175466", "Johnny Tremain", 
                 "Esther Forb", 
                 true, "Adventure"); 
         
         inventory[18] = new Book("13282501175466", "The Time Machine", 
                 "H.G.Wells", 
                 false, "Adventure"); 
         
         inventory[19] = new Book("65482501175466", "The Outsiders", 
                 "S.E. Hinton", 
                 false, "Young Adult Fiction"); 
         
         inventory[20] = new Book("64882501175466", "Divergent", 
                 "Veronica Rot", 
                 true, "Young Adult Fiction");
         
         inventory[21] = new Book("65782501175466", "The Giver", "Lois Lowry", 
                 true,
                 "Young Adult Fiction");
         
         inventory[22] = new Book("95482501175466", "Uglies", "Scott Wester", 
                 true, "Young Adult Fiction");
         
         inventory[23] = new Book("91482501175466", "Paper Towns", "John Green", 
                 true, "Young Adult Fiction");
    }
    
}
