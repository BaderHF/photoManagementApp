public class PhotoManager {

	LinkedList<Photo> photos;

	// Constructor
	public PhotoManager() {
		photos = new LinkedList<Photo>();
	}

	// Return all managed photos
	public LinkedList<Photo> getPhotos() {
		return photos;
	}

	// Add a photo
	public void addPhoto(Photo p) {
		if (!IsPhoAvailable(p.getPath(), photos))
			photos.insert(p);
	}

	// Delete a photo
	public void deletePhoto(String path) {
		if (!this.IsPhoAvailable(path, photos))
			return;

		if (!photos.empty()) {
			boolean found = false;

			photos.findFirst();
			while (!found && !photos.last()) {
				Photo pho = photos.retrieve();
				if (pho.getPath().equalsIgnoreCase(path)) {
					found = true;
					photos.remove();
				}
				photos.findNext();
			}

			if (!found) {
				Photo pho = photos.retrieve();
				if (pho.getPath().equalsIgnoreCase(path)) {
					found = true;
					photos.remove();
				}
			}
		}
	}
	
	private boolean IsPhoAvailable(String path, LinkedList<Photo> L) {
		if (L.empty())
			return false;

		L.findFirst();
		while (!L.last()) {
			if (L.retrieve().getPath().equalsIgnoreCase(path))
				return true;

			L.findNext();
		}

		if (L.retrieve().getPath().equalsIgnoreCase(path))
			return true;

		return false;
	}
}
