 // sort the files into reverse order ignoring case
    Collections.sort(fileList, new Comparator<File>() {
        public int compare(File o1, File o2) {
            return o2.getName().compareToIgnoreCase(o1.getName());
        }
    });
