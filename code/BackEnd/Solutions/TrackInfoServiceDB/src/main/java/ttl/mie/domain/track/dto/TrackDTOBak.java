//package ttl.mie.domain.track.dto;
//
//import java.math.BigDecimal;
//import java.time.Duration;
//import java.util.ArrayList;
//import java.util.List;
//import ttl.mie.domain.track.Format;
//
//public record TrackDTOBak(int trackId, Duration length, Format format, String title,
//                       String album, String year,
//                       String genre, BigDecimal price, List<ArtistDTO> artists) implements Comparable<TrackDTO> {
//
//   public TrackDTOBak(int trackId, Duration length, Format format, String title, String album,
//                   String year, String genre, BigDecimal price, List<ArtistDTO> artists) {
//      this.trackId = trackId;
//      this.length = length;
//      this.format = format;
//      this.title = title;
//      this.album = album;
//      this.year = year;
//      this.genre = genre;
//      this.price = price;
//      this.artists = new ArrayList<>();
//      if (artists != null) {
//         this.artists.addAll(artists);
//      }
//   }
//
//   public TrackDTOBak(int trackId, int length, String format, String title, String album,
//                   String year, String genre, BigDecimal price, List<ArtistDTO> artists) {
//      this(trackId, Duration.ofSeconds(length), Format.valueOf(format.toUpperCase()), title, album, year, genre, price, artists);
//   }
//
//   public TrackDTOBak(int id, int length, String format, String title, String album,
//                   String year, String genre, String price, List<ArtistDTO> artists) {
//      this(id, Duration.ofSeconds(length), Format.valueOf(format.toUpperCase()), title, album, year, genre, new BigDecimal("0"), artists);
//   }
//
//   public TrackDTOBak(int id, int length, String format, String title, String album,
//                   String year, String genre, List<ArtistDTO> artists) {
//      this(id, Duration.ofSeconds(length), Format.valueOf(format.toUpperCase()), title, album, year, genre, new BigDecimal("0"), artists);
//   }
//
//   public void addArtist(ArtistDTO artist) {
//      //Check to see if you have an artist with this name
//      //already, if so, replace it.
//      boolean found = false;
//      for (int i = 0; i < artists.size(); i++) {
//         if (artists.get(i) != null && artist.name() != null) {
//            if (artists.get(i).name().equalsIgnoreCase(artist.name())) {
//               artists.set(i, artist);
//               found = true;
//               break;
//            }
//         }
//      }
//      if (!found) {
//         artists.add(artist);
//      }
//   }
//
//   @Override
//   public int compareTo(TrackDTO other) {
//      return this.length.compareTo(other.length);
//   }
//
//   public static TrackDTO copyWithId(int id, TrackDTO ati) {
//      var newAti = new TrackDTO(id,
//            ati.length(),
//            ati.format(),
//            ati.title(),
//            ati.album(),
//            ati.year(),
//            ati.genre(),
//            ati.price(),
//            ati.artists());
//
//      return newAti;
//   }
//
//   public static TrackDTO copyWithPrice(BigDecimal price, TrackDTO ati) {
//      var newAti = new TrackDTO(ati.trackId(),
//            ati.length(),
//            ati.format(),
//            ati.title(),
//            ati.album(),
//            ati.year(),
//            ati.genre(),
//            price,
//            ati.artists());
//
//      return newAti;
//   }
//
//   public static TrackDTO copywithFormat(String format, TrackDTO ati) {
//      return copywithFormat(Format.valueOf(format.toUpperCase()), ati);
//   }
//
//   public static TrackDTO copywithFormat(Format format, TrackDTO ati) {
//      var newAti = new TrackDTO(ati.trackId(),
//            ati.length(),
//            format,
//            ati.title(),
//            ati.album(),
//            ati.year(),
//            ati.genre(),
//            ati.price(),
//            ati.artists());
//
//      return newAti;
//   }
//
//   public static TrackDTO copyWithTitle(String title, TrackDTO ati) {
//      var newAti = new TrackDTO(ati.trackId(),
//            ati.length(),
//            ati.format(),
//            title,
//            ati.album(),
//            ati.year(),
//            ati.genre(),
//            ati.price(),
//            ati.artists());
//
//      return newAti;
//   }
//
//   public static TrackBuilder builder() {
//      return new TrackBuilder();
//   }
//
//   public static class TrackBuilder {
//      private int id;
//      private Duration length;
//      private Format format;
//      private String title;
//      private String album;
//      private String year;
//      private String genre;
//      private BigDecimal price;
//      private List<ArtistDTO> artists = new ArrayList<>();
//
//      public TrackBuilder id(int id) {
//         this.id = id;
//         return this;
//      }
//
//      public TrackBuilder length(Duration length) {
//         this.length = length;
//         return this;
//      }
//
//      public TrackBuilder length(int length) {
//         return length(Duration.ofSeconds(length));
//      }
//
//      public TrackBuilder format(Format format) {
//         this.format = format;
//         return this;
//      }
//
//      public TrackBuilder format(String format) {
//         return format(Format.valueOf(format.toUpperCase()));
//      }
//
//      public TrackBuilder title(String title) {
//         this.title = title;
//         return this;
//      }
//
//      public TrackBuilder album(String album) {
//         this.album = album;
//         return this;
//      }
//
//      public TrackBuilder artist(String artistName) {
//         var artist = new ArtistDTO(artistName);
//         artists.add(artist);
//         return this;
//      }
//
//      public TrackBuilder artist(ArtistDTO artist) {
//         artists.add(artist);
//         return this;
//      }
//
//      public TrackBuilder year(String year) {
//         this.year = year;
//         return this;
//      }
//
//      public TrackBuilder genre(String genre) {
//         this.genre = genre;
//         return this;
//      }
//
//      public TrackBuilder price(BigDecimal price) {
//         this.price = price;
//         return this;
//      }
//
//      public TrackBuilder price(String price) {
//         return this.price(new BigDecimal(price));
//      }
//
//
//      public TrackBuilder price(double price) {
//         return this.price(new BigDecimal(String.valueOf(price)));
//      }
//
//      public TrackDTO build() {
//         return new TrackDTO(id, length, format, title, album, year, genre, price, artists);
//      }
//   }
//}