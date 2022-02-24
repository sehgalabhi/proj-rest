import org.springframework.cloud.contract.spec.Contract

Contract.make {

    request {
        method 'POST'
        url '/cars'
        body    """
                {
                    "name" : "i20",
                    "carBrand" : "HYUNDAI",
                    "seats" : 5
                }
          """
        headers {
            contentType applicationJson()
        }
    }

    response {
        status 201

        headers {
            contentType applicationJson()
        }
    }
}