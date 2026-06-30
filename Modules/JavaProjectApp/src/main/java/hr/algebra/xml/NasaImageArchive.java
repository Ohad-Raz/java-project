package hr.algebra.xml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

// JAXB root wrapper for exported NASA images (Exercise 11-12 pattern).
@XmlRootElement(name = "NasaImageArchive")
@XmlAccessorType(XmlAccessType.FIELD)
public class NasaImageArchive {

    @XmlElement(name = "image")
    private List<NasaImageExportDto> images = new ArrayList<>();

    public NasaImageArchive() {
    }

    public NasaImageArchive(List<NasaImageExportDto> images) {
        this.images = images;
    }

    public List<NasaImageExportDto> getImages() {
        return images;
    }

    public void setImages(List<NasaImageExportDto> images) {
        this.images = images;
    }
}
