INSERT INTO manufacturer (id, name) VALUES
(1, 'Bayer HealthCare Pharmaceuticals Inc.'),
(2, 'Jazz Pharmaceuticals plc'),
(3, 'Santen Inc.');

INSERT INTO drugs (id, date_of_approval, description, name, treatment_for, manufacturer_id) VALUES
(1, '2021-07-09', 'Kerendia (finerenone) is a non-steroidal, selective mineralocorticoid receptor antagonist (MRA) for the treatment of patients with chronic kidney disease (CKD) associated with type 2 diabetes (T2D).', 'Kerendia Tablets', 'Chronic Kidney Disease Associated with Type 2 Diabetes', 1),
(2, '2021-06-30', 'Rylaze (asparaginase erwinia chrysanthemi (recombinant)-rywn) is an asparagine specific enzyme indicated as a component of a multi-agent chemotherapeutic regimen for the treatment of acute lymphoblastic leukemia (ALL) and lymphoblastic lymphoma (LBL).', 'Rylaze Injection', 'Acute Lymphoblastic Leukemia', 2),
(3, '2021-07-23', 'Verkazia (cyclosporine) is a calcineurin inhibitor immunosuppressant indicated for the treatment of vernal keratoconjunctivitis (VKC) in children and adults.', 'Verkazia Ophthalmic Emulsion', 'Vernal Keratoconjunctivitis', 3);


INSERT INTO admin (id, name, password, role) VALUES
(1, 'admin', '$2a$10$zl.ZT.8EHAnGTDBV2de.A.8q73wgHewiOgXgaa0leNV7YqYthuUs.', 'ADMIN');