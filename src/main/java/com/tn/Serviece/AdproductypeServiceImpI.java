package com.tn.Serviece;

import com.tn.Entity.Adproductype;
import com.tn.Repository.Adproductyperepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdproductypeServiceImpI {

        @Autowired
        private Adproductyperepo adproductyperepo;

        public List<Adproductype> getAllAdProductTypes() {

            return adproductyperepo.findAll();
        }


}
