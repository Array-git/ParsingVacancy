package org.parse.vacancies;

import org.parse.vacancies.entity.Vacancy;
import org.parse.vacancies.model.*;

import java.util.ArrayList;
import java.util.List;

public class Aggregator {
    public List<Vacancy> getVacancies(String keyWords, String city, Provider[] stringProviders, Lvl lvl, boolean remove) {
        Model model = new Model(stringProviders);
        //ParsController controller = new ParsController(model);
        return model.getVacancies(keyWords, city, lvl, remove);
    }

    /*public static final Certificate[] readCertificatesFromPKCS7(byte[] binaryPKCS7Store) throws Exception
    {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(binaryPKCS7Store);)
        {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            Collection<?> c = cf.generateCertificates(bais);
                        List<Certificate> certList = new ArrayList<Certificate>();

            if (c.isEmpty())
            {
                // If there are now certificates found, the p7b file is probably not in binary format.
                // It may be in base64 format.
                // The generateCertificates method only understands raw data.
            }
            else
            {

                Iterator<?> i = c.iterator();

                while (i.hasNext())
                {
                    Certificate cert = (Certificate) i.next();
                    X509CertImpl certificate = new X509CertImpl(cert.getEncoded());
                    certificate.getSerialNumber();
                    certList.add((Certificate) i.next());
                }
            }

            java.security.cert.Certificate[] certArr = new java.security.cert.Certificate[certList.size()];

            return certList.toArray(certArr);
        }
    }*/

    public Provider[] toProvider(String[] strategies) {
        List<Provider> providers = new ArrayList<>();
        if (strategies.length == 0) {
            return null;
        }
        for (int i = 0; i < strategies.length; i++) {
            if (strategies[i].equals("hh.ru")) {
                providers.add(new Provider(new HHStrategy()));
                continue;
            }
            if (strategies[i].equals("career.habr.com")) {
                providers.add(new Provider(new HabrCareerStrategy()));
            }
        }
        return providers.toArray(new Provider[providers.size()]);
    }
}
