# elr-r1-quest-validation-plugins

## Dependencies

hl7-v2-validation 1.5.0
https://hit-nexus.nist.gov/repository/public/gov/nist/hl7-v2-validation/1.5.0/hl7-v2-validation-1.5.0.jar

hl7-v2-parser 1.5.0
https://hit-nexus.nist.gov/repository/public/gov/nist/hl7-v2-parser/1.5.0/hl7-v2-parser-1.5.0.jar

hl7-v2-profile 1.5.0
https://hit-nexus.nist.gov/repository/public/gov/nist/hl7-v2-profile/1.5.0/hl7-v2-profile-1.5.0.jar

## How to use a plugin in a XML profile (Constraints.xml file)
Under to correct context, this is the XML script to add:

```
<Constraint ID="ELR-40">
	<Description>INSERT DESCRIPTION HERE</Description>
	<Assertion>
		<Plugin QualifiedClassName="gov.nist.hit.elr.quest.plugin.cs.ELR_040" />
	</Assertion>
</Constraint>
```

It is important that:
1. The constraint is added under the correct context.
2. The *QualifiedClassName* is the full name (package + class name) of the plugin's code.

When using in validation, check for spec errors. If there is a spec error, something is wrong either in the plugin Java code, or in the XML script (wrong context or wrong *QualifiedClassName*)

## How to use a plugin in NIST IGAMT
Under the correct context, add a *Free Text* conformance statement.

Fill in the *Identifier*, the conformance statement *Strength*, the *Description* and the *XML Script*.

The XML script is the assertion part. Again, the *QualifiedClassName* is important. Check for spec errors in the NIST validation!

```
<Assertion>
	<Plugin QualifiedClassName="gov.nist.hit.elr.quest.plugin.cs.ELR_040" />
</Assertion>
```
![plugin](https://user-images.githubusercontent.com/13591511/200346443-f7f97f5c-8e2e-482a-b654-930516014244.png)

