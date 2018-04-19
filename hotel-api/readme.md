# Hotel API

This project was developed to demonstrate knowledge of using Java APIs using SpringBoot and other technologies. 

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them

```
Java 8
Gradle
PostgreSQl
```

### Installing

A step by step series of examples that tell you have to get a development env running



```
Clone project into your enviroment
Load the gradle task
Run the gradle build task.
```

### Scripts

Run the scripts.

```
CREATE SEQUENCE public.checkin_id_seq
    INCREMENT 1
    START 3
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.checkin_id_seq
    OWNER TO postgres;
    
    
CREATE SEQUENCE public.person_id_seq
    INCREMENT 1
    START 6
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.person_id_seq
    OWNER TO postgres;
    
-- Table: public.person

-- DROP TABLE public.person;

CREATE TABLE public.person
(
    id bigint NOT NULL DEFAULT nextval('person_id_seq'::regclass),
    name character varying COLLATE pg_catalog."default" NOT NULL,
    document character varying COLLATE pg_catalog."default" NOT NULL,
    fone character varying COLLATE pg_catalog."default",
    CONSTRAINT person_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.person
    OWNER to postgres;
    

-- Table: public.checkin

-- DROP TABLE public.checkin;

CREATE TABLE public.checkin
(
    id bigint NOT NULL DEFAULT nextval('checkin_id_seq'::regclass),
    id_person bigint NOT NULL,
    have_vehicle boolean NOT NULL DEFAULT false,
    entry_date timestamp(4) without time zone NOT NULL,
    exit_date timestamp(4) without time zone NOT NULL,
    CONSTRAINT checkin_pkey PRIMARY KEY (id),
    CONSTRAINT fk_person FOREIGN KEY (id_person)
        REFERENCES public.person (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.checkin
    OWNER to postgres;

-- Index: fki_fk_person

-- DROP INDEX public.fki_fk_person;

CREATE INDEX fki_fk_person
    ON public.checkin USING btree
    (id_person)
    TABLESPACE pg_default;
    
            
```


## Deployment

Run the gradle build bootRun.

 
##Endpoints

Create Person

POST - http://localhost:8080/api/person
```
{
    "name": "ellyson",
    "document": "65493215",
    "fone": "47988990987"
}
```

Update Person

PUT - http://localhost:8080/api/person
```
{
    "id": 3
    "name": "ellyson",
    "document": "65493215",
    "fone": "47988990987"
}
```

Delete person

DELETE - http://localhost:8080/api/person
```
{
    "id": 6,
    "name": "ellyson",
    "document": "65493215",
    "fone": "47988990987"
}
```

Get person

GET - http://localhost:8080/api/person/3
```
{
    "id": 3,
    "name": "Ellyson",
    "document": "123123",
    "fone": "47988990987"
}
```


List of person
GET - http://localhost:8080/api/person
```
[
    {
        "id": 3,
        "name": "Ellyson",
        "document": "123123",
        "fone": "47988990987"
    },
    {
        "id": 4,
        "name": "joão",
        "document": "123123",
        "fone": "47988990987"
    },
    {
        "id": 5,
        "name": "joão",
        "document": "987",
        "fone": "47988990987"
    },
    {
        "id": 6,
        "name": "ellyson",
        "document": "65493215",
        "fone": "47988990987"
    }
]
```

List by name/document/fone

GET - http://localhost:8080/api/list/person/name/ellyson
GET - http://localhost:8080/api/list/person/document/234234
GET - http://localhost:8080/api/list/person/fone/33445678
```
[
    {
        "id": 3,
        "name": "Ellyson",
        "document": "123123",
        "fone": "47988990987"
    },
    {
        "id": 6,
        "name": "ellyson",
        "document": "65493215",
        "fone": "47988990987"
    }
]
```

CheckIn

Create checkIn

POST - http://localhost:8080/api/checkIn
```
{
    "person": {
        "id": 3
    },
    "entryDate": "2018-04-14T12:00:00",
    "exitDate": "2018-04-16T18:00:00",
    "haveVehicle": true,
    "totalPrice": 0
}
```

Update checkIn

PUT - http://localhost:8080/api/checkIn
```
{
    "id": 3,
    "person": {
        "id": 3
    },
    "entryDate": "2018-04-14T12:00:00",
    "exitDate": "2018-04-16T18:00:00",
    "haveVehicle": true,
    "totalPrice": 0
}
```

Delete checkIn
DELETE - http://localhost:8080/api/checkIn
```
{
    "id": 3,
    "person": {
        "id": 3
    },
    "entryDate": "2018-04-14T12:00:00",
    "exitDate": "2018-04-16T18:00:00",
    "haveVehicle": true,
    "totalPrice": 0
}
```

Get list of person IN/OUT hotel.

GET - http://localhost:8080/api/checkIn/in
GET - http://localhost:8080/api/checkIn/out

```
[
    {
        "id": 3,
        "person": {
            "id": 3,
            "name": "Ellyson",
            "document": "123123",
            "fone": "47988990987"
        },
        "entryDate": "2018-04-14T12:00:00",
        "exitDate": "2018-04-16T18:00:00",
        "haveVehicle": true,
        "totalPrice": 610
    }
]
```

## Authors

* **Ellyson Henrique Cesar** 

