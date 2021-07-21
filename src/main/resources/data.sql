INSERT INTO manufacturer (id, name) VALUES
(1, 'Bayer HealthCare Pharmaceuticals Inc.'),
(2, 'Jazz Pharmaceuticals plc'),
(3, 'Santen Inc.');

INSERT INTO drugs (id, date_of_approval, description, name, treatment_for, manufacturer_id) VALUES
(1, '2021-06-21', 'Kerendia', 'Kerendia (finerenone) Tablets', 'Chronic Kidney Disease Associated with Type 2 Diabetes', 1),
(2, '2021-05-20', 'Rylaze', 'Rylaze Tablets', 'Rylaze (asparaginase erwinia', 1),
(3, '2021-04-19', 'Verkazia', 'Verkazia Tablets', 'Verkazia (asparaginase erwinia', 3),
(4, '2021-04-19', 'Astepro Allergy ', 'Astepro Allergy Tablets', 'Astepro Allergy (asparaginase erwinia', 2);


INSERT INTO admin (id, name, password, role) VALUES
(1, 'admin', '$2a$10$zl.ZT.8EHAnGTDBV2de.A.8q73wgHewiOgXgaa0leNV7YqYthuUs.', 'ADMIN'),
(2, 'loh', '$2a$10$QGED47r6o1NBWzsr/q8RtORMY58ppPXHA0wgCVh0rvTV2enf2m50K', 'ADMIN');