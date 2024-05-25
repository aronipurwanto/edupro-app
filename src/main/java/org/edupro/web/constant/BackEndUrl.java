package org.edupro.web.constant;

import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class BackEndUrl {
    @Value("${spring.application.back-end-url}")
    private String baseUrl;

    public String levelUrl(){
        return Strings.concat(baseUrl,"/level");
    }

    public String lookupUrl(){
        return Strings.concat(baseUrl,"/lookup");
    }

    public String sesiUrl(){
        return Strings.concat(baseUrl,"/sesi");
    }

    public String mapelUrl(){
        return Strings.concat(baseUrl,"/mapel");
    }

    public String ruanganUrl(){
        return Strings.concat(baseUrl,"/ruangan");
    }

    public String gedungUrl(){
        return Strings.concat(baseUrl,"/gedung");
    }

    public String kurikulumUrl(){
        return Strings.concat(baseUrl,"/kurikulum");
    }

    public String kelompokUrl(){
        return Strings.concat(baseUrl, "/kelompok/mapel");
    }

    public String siswaUrl(){
        return Strings.concat(baseUrl, "/siswa");
    }

    public String tahunAjaranUrl(){
        return Strings.concat(baseUrl, "/ta");
    }
  
    public String courseUrl(){
        return Strings.concat(baseUrl, "/course");
    }

    public String sectionUrl(){
        return Strings.concat(baseUrl, "/course/{courseId}/section");
    }
  
    public String personUrl(){
        return Strings.concat(baseUrl, "/person");
    }

    public String lembagaUrl(){
        return Strings.concat(baseUrl, "/lembaga");
    }

    public String kelasUrl(){
        return Strings.concat(baseUrl, "/kelas");
    }
}
