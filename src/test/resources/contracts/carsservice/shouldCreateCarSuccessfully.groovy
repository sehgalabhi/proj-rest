import org.springframework.cloud.contract.spec.Contract

Contract.make {

    request {
        method 'POST'
        url '/basicAuthCars'
        body(
                // on consumer stubs, will be any UUId but for provider verification value as below
                "requestUuid": anyUuid(),
                "name": "i20",
                "carBrand": "HYUNDAI",
                "seats": 5,
                //todo fix
         //       "requestedTime": anyDateTime()
        )

        headers {
            contentType applicationJson()
        }
    }

    response {
        status 201
        body(
                // on consumer side will be hardcoded, but on provider verification could be any response
                "requestUuid": fromRequest().body('$.requestUuid'),
                "responseUuid": $(consumer('567e4567-e89b-14d3-a456-426614174000'), producer(anyUuid())),
                "name": fromRequest().body('$.name'),
                "carBrand": fromRequest().body('$.carBrand'),
                "seats": fromRequest().body('$.seats'),
//todo fix for datetime
//                "creationTime": anyDateTime(),

        )

        headers {
            contentType applicationJson()
        }
    }
}