public class Photo {

	private String path;
	LinkedList<String> tagList = new LinkedList<>();

	// Constructor
	public Photo(String path, LinkedList<String> tags) {
		this.path = path;

		if (!tags.empty()) {
			tags.findFirst();
			while (!tags.last()) {
				tagList.insert(tags.retrieve());
				tags.findNext();
			}
			tagList.insert(tags.retrieve());
		}
	}

	// Return the path (full file name) of the photo. A photo is uniquely identified by its path.
	public String getPath() {
		return path;
	}

	// Return all tags associated with the photo
	public LinkedList<String> getTags() {
		LinkedList<String> copiedTags = new LinkedList<String>();

		if (!tagList.empty()) {
			tagList.findFirst();
			while (!tagList.last()) {
				copiedTags.insert(tagList.retrieve());
				tagList.findNext();
			}
			copiedTags.insert(tagList.retrieve());
		}
		return copiedTags;
	}

	@Override
	public String toString() {

		String str = "Photo{" + "path=" + path + ", tagList=";

		tagList.findFirst();
		while (!tagList.last()) {
			str += tagList.retrieve().toString() + "; ";
			tagList.findNext();
		}

		str += tagList.retrieve().toString() + "}";
		return str;
	}
}