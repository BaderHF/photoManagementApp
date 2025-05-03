

public class Album {
        private String name;
        private String condition;
        private PhotoManager manager;
        private int NbComps;

        // Constructor
        public Album(String name, String condition, PhotoManager manager)
        {
            this.name = name;
            this.condition = condition;
            this.manager = manager;
            NbComps =0;
        }
        
        // Return the name of the album
        public String getName()
        {
            return name;
        }
        
        // Return the condition associated with the album
        public String getCondition()
        {
            return condition;
        }

        // Return the manager
        public PhotoManager getManager()
        {
            return manager;
        }
   
        // Return all photos that satisfy the album condition
        public LinkedList<Photo> getPhotos()
        {
                LinkedList<Photo> LLphotos = new LinkedList<Photo>();
                {
                    LinkedList<Photo> copyPhotos = manager.getPhotos();
                    if (! copyPhotos.empty())
                    {
                    	copyPhotos.findFirst();
                        while (! copyPhotos.last())
                        {
                        	LLphotos.insert(new Photo(copyPhotos.retrieve().getPath(), copyPhotos.retrieve().getTags()));
                            copyPhotos.findNext();
                        }
                        LLphotos.insert(new Photo(copyPhotos.retrieve().getPath(), copyPhotos.retrieve().getTags()));
                    }
                }
                NbComps =0 ;
                
                if (this.condition.compareTo("") != 0)
                {
                    String [] Array = condition.split(" AND ");
                    
                    LLphotos.findFirst();
                    while ( ! LLphotos.last())
                    {
                        Photo photo = LLphotos.retrieve();
                        if ( ! allAvilable (photo.tagList , Array ))
                        	LLphotos.remove();
                        else
                        	LLphotos.findNext();
                    }
                    Photo photo11 = LLphotos.retrieve();
                    if ( ! allAvilable (photo11.tagList , Array ))
                    	LLphotos.remove();
                    else
                    	LLphotos.findNext();
                }
                return LLphotos;
        }
       
        // Return the number of tag comparisons used to find all photos of the album
        public int getNbComps()
        {
            return NbComps;
        }

        private boolean allAvilable ( LinkedList<String> tagList , String [] Array )
        {
            boolean cont = true;
            if (tagList.empty())
                cont = false;
            else
            {
                for ( int i = 0 ; i < Array.length && cont ; i++)
                {
                    boolean found_in_tags = false;

                    tagList.findFirst();

                    while (!tagList.last())
                    {
                        this.NbComps ++ ;    
                        if (tagList.retrieve().compareToIgnoreCase(Array[i]) == 0)
                        {
                            found_in_tags = true;
                            break;
                        }
                        tagList.findNext();
                    }
                    if (! found_in_tags )
                    {
                        this.NbComps ++ ;
                        if (tagList.retrieve().compareToIgnoreCase(Array[i]) == 0)
                            found_in_tags = true;
                    }
                    if ( ! found_in_tags )
                        cont = false;
                }
            }
            return cont;
        }
       
}