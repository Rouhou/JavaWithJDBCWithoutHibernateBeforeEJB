CREATE TABLE client (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100),
    prenom VARCHAR(100),
    telephone VARCHAR(20),
    date_naissance DATE
);

CREATE TABLE compte (
    id SERIAL PRIMARY KEY,
    numeroCompte VARCHAR(50),
    solde DOUBLE PRECISION,
    dateCreation DATE,
    clientId BIGINT
);

CREATE TABLE operation (
   id SERIAL PRIMARY KEY,
   dateOperation DATE,
   montant DOUBLE PRECISION,
   type VARCHAR(20),
   compteId BIGINT
);